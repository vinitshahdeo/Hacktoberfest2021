// node procsHtml.js --source="Download.html" 

const fs = require("fs");
const jsdom = require("jsdom");
const minimist = require("minimist");

let args = minimist(process.argv)

fs.readFile(args.source,"utf-8",function(err,data){

    let JSDOM = jsdom.JSDOM;
    let dom = new JSDOM(data);

    let document = dom.window.document;

    let DataRecieved = document.querySelectorAll("div.match-info > div.description");

    for(let i = 0; i<DataRecieved.length; i++){
        console.log(DataRecieved[i].textContent);
    }




})
