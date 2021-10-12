#!/usr/bin/env kotlin

//@file:Repository("https://repo1.maven.org/maven2/")
@file:Repository("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
@file:DependsOn("org.jetbrains:markdown-jvm:0.2.4")
@file:DependsOn("org.jetbrains.kotlin-wrappers:kotlin-css-jvm:1.0.0-pre.254-kotlin-1.5.31")

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.*
import kotlinx.html.body
import kotlinx.html.em
import kotlinx.html.stream.appendHTML
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.th
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.io.File
import java.util.*

data class Repository(
    val repository: Pair<String, String>,
    val issues: List<Issue>
)

data class Issue(
    val issue: Pair<String, String>,
    var language: MutableList<String>
)


fun parseLink(input: String): Pair<String, String> {
    val (_, sec) = input.split("href=\"")
    val (thir, four) = sec.split("\">")
    val (five, _) = four.split("</a>")
    return thir to five
}

fun parseCode(input: String): String {
    val (_, sec) = input.split("<code>")
    val (third, _) = sec.split("</code>")
    return third
}

fun getIssueRepository(): List<Repository> {
    File("").absolutePath.also(::println)
    val markdownContent = File("explore/ISSUES.md").readText()
    val flavour = CommonMarkFlavourDescriptor()
    val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(markdownContent)
    val html = parsedTree.children.flatMap { it.children }
        .map { HtmlGenerator(markdownContent, it, flavour).generateHtml() }
        .filter { it.isNotEmpty() }
        .joinToString("\n")

    val repos = html.lines().filter { string ->
        (string.contains("<a") && !string.contains("/issues"))
    }

    val issues = html.lines().filter { string ->
        string.contains("/issues")
    }.map { parseLink(it) }

    val issueWithLang = issues.map { issue ->
        val lines = html.lines()
        val startIndex = lines.indexOfFirst { it.contains(issue.first) } + 1
        var endIndex = startIndex
        while (lines.getOrNull(endIndex)?.contains("<code>") == true) {
            endIndex += 1
        }
        val lang = mutableListOf<String>()
        if (startIndex == endIndex){
            lang.add(parseCode(lines.get(endIndex+1)))
        }else{
            (startIndex until endIndex).map {
                lang.add(parseCode(lines.get(it)))
            }
        }
        Issue(issue, lang)
    }

    val repoLink = mutableListOf<Repository>()
    repos.forEach {
        val repoFormatted = parseLink(it)
        val exist = repoLink.firstOrNull { it.repository.first.contains(repoFormatted.first) }
        if (exist == null) {
            val repoIssues = issueWithLang.filter { it.issue.first.contains(repoFormatted.first) }
            repoLink.add(Repository(repoFormatted, repoIssues))
        }
    }
    return repoLink
}

fun getLanguages(repos: List<Repository>) = repos.flatMap { it.issues }
    .flatMap { it.language }
    .map { it.lowercase() }
    .toSet()
    .toList()

fun createOrGetDirectories(language: List<String>): List<File> {
    val exploreDirectory = File("explore").absolutePath
    val issues = File("$exploreDirectory${File.separator}issues")
    issues.deleteRecursively().also {
        println("#### RESET ISSUE : $it #####")
    }
    val directories = language.map {
        val folder = File("$exploreDirectory${File.separator}issues${File.separator}$it")
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return@map folder
    }
    return directories
}

fun generateMarkDown(directories: List<File>, repository: List<Repository>) {
    val stylesDir = File("explore/.meta")
    if (!stylesDir.exists()) {
        stylesDir.mkdirs()
    }
    val style = CssBuilder().apply {
        body {
            backgroundColor = Color("#f3f0e0")
            margin(20.px)
            padding(20.px)
        }

        table {
            padding(20.px)
            width = 100.pct
            borderCollapse = BorderCollapse.collapse
        }

        th {
            border(
                width = 1.px,
                style = BorderStyle.solid,
                color = Color.black
            )
            textAlign = TextAlign.left
            margin(8.px)
            backgroundColor = Color("#dddddd")
            color = Color("#101010")
        }

        td {
            border(
                width = 1.px,
                style = BorderStyle.solid,
                color = Color("#dddddd")
            )
            textAlign = TextAlign.left
            margin(8.px)
            color = Color("#008080")
        }

        em {
            margin(4.px)
        }

        (".center"){
            display = Display.block
            marginLeft = LinearDimension.auto
            marginRight = LinearDimension.auto
            width = 50.pct
        }

    }.toString()
    File(stylesDir.absolutePath + "${File.separator}style.css").writeText(style)
    directories.forEach {
        val html = buildString {
            appendHTML(false).html {
                appendLine("<!DOCTYPE html>")
                head {
                    link {
                        href = "../../.meta/style.css"
                        rel = "stylesheet"
                    }
                }
                body {
                    img {
                        src = "https://github.com/ch8n/Hacktoberfest2021/blob/main/assets/logo.png?raw=true"
                        classes = setOf("center")
                    }
                    h1 { +it.nameWithoutExtension.replaceFirstChar { it.titlecase() } }
                    h4 {
                        em { +"This is a generated file from " }
                        a {
                            href = "../../ISSUES.md"
                            +"ISSUES"
                        }
                        em { +" kindly don't delete it" }
                        em {
                            +"- Automation script by "
                            a {
                                href = "https://chetangupta.net/about"
                                target = "_blank"
                                +"Chetan Gupta"
                            }
                        }
                    }
                    table {
                        tr {
                            th { +"Serial No." }
                            th { +"Issue" }
                            th { +"Repository" }
                            th { +"Language" }
                        }
                        val issues = mutableListOf<Pair<Issue, Repository>>()
                        val filename = it.nameWithoutExtension
                        repository.forEachIndexed { _, repo ->
                            repo.issues.fold(issues) { acc, _issue ->
                                val belongs = filename in _issue.language.map { it.lowercase() }
                                if (belongs) {
                                    acc.add(_issue to repo)
                                }
                                return@fold acc
                            }
                        }

                        issues.forEachIndexed { index, _issue ->
                            val (issueUrl, issueName) = _issue.first.issue
                            tr {
                                td {
                                    +"${index + 1}"
                                }
                                td {
                                    a {
                                        href = issueUrl
                                        target = "_blank"
                                        +issueName
                                    }
                                }
                                td {
                                    a {
                                        val (repoUrl, repoName) = _issue.second.repository
                                        href = repoUrl
                                        target = "_blank"
                                        +repoName
                                    }
                                }
                                td {
                                    _issue.first.language.forEach {
                                        em { +it }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        File(it.absolutePath + "${File.separator}index.md").writeText(html)
    }
}

fun generateExploreMarkDown(directories: List<File>) {
    val html = buildString {
        appendHTML(false).html {
            appendLine("<!DOCTYPE html>")
            head {
                link {
                    href = ".meta/style.css"
                    rel = "stylesheet"
                }
            }
            body {
                img {
                    src = "https://github.com/ch8n/Hacktoberfest2021/blob/main/assets/logo.png?raw=true"
                    classes = setOf("center")
                }
                h4 {
                    em { +"This is a generated file from " }
                    a {
                        href = "../../ISSUES.md"
                        +"ISSUES"
                    }
                    em { +" kindly don't delete it" }
                    em {
                        +"- Automation script by "
                        a {
                            href = "https://chetangupta.net/about"
                            target = "_blank"
                            +"Chetan Gupta"
                        }
                    }
                }
                table {
                    tr {
                        th { +"Serial No." }
                        th { +"Technology" }
                    }
                    directories.sortedBy { it.name }.forEachIndexed { index, file ->
                        tr {
                            td {
                                +"${index + 1}"
                            }
                            td {
                                a {
                                    href = "${file.path.split("/").drop(7).joinToString("/")}/index.md"
                                    target = "_blank"
                                    +file.name.replaceFirstChar { it.titlecase() }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    File("explore/explore.md").writeText(html)
}

println("###### Parsing issues and repository ######")
val repo = getIssueRepository()
println("###### Parsing languages ###### ")
val lang = getLanguages(repo)
println("###### Creating directories ######")
val dir = createOrGetDirectories(lang)
println("###### Generating HTML and Markdown ######")
generateMarkDown(dir, repo)
println("###### Generating Explore HTML ######")
generateExploreMarkDown(dir)
println("###### DONE! #######")
println("###### Directory added #######")
println(dir.joinToString("\n"))

