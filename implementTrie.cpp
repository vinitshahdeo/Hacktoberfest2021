#include<bits/stdc++.h>
using namespace std;
struct TrieNode{
    bool isEnd;
    TrieNode *child[26]; 
    TrieNode(){
        for(int i=0;i<26;i++){
            child[i]=NULL;
        }
        isEnd=false;
    }
};

class Trie {
public:
    TrieNode *root;
    /** Initialize your data structure here. */
    Trie() {
        root=NULL;
    }

    /** Inserts a word into the trie. */
    void insert(string word) {
        int ind;
        if(!this->root){
            this->root=new TrieNode();
        }
        TrieNode *t=this->root;
        for(int i=0;i<word.size();i++){
            ind=word[i]-'a';
            if(!t->child[ind]){
                t->child[ind] = new TrieNode();
            }
            t=t->child[ind];
        }
        t->isEnd=true;
    }

    /** Returns if the word is in the trie. */
    bool search(string word) {
        int ind;
        if(!this->root){
            return false;
        }
        TrieNode *t=this->root;
        for(int i=0;i<word.size();i++){
            ind=word[i]-'a';
            if(!t->child[ind]){
                return false;
            }
            t=t->child[ind];
        }
        return t->isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    bool startsWith(string prefix) {
        int ind;
        if(!this->root){
            return false;
        }
        TrieNode *t=this->root;
        for(int i=0;i<prefix.size();i++){
            ind=prefix[i]-'a';
            if(!t->child[ind]){
                return false;
            }
            t=t->child[ind];
        }
        return true;
    }
};

int main(){
  /**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */
}
