#include<iostream.h>
#include<bits/stdc++.h>
using namespace std;

bool validateRow(vector<vector<char>> &board,int row){
    map<char,int> m;
    for(int i=0;i<9;i++){
        if(board[row][i]=='.'){
            continue;
        }
        if(m.find(board[row][i])!=m.end()){
            return false;
        }
        m[board[row][i]]++;
    }
    return true;
}

bool validateCol(vector<vector<char>> &board,int col){
    map<char,int> m;
    for(int i=0;i<9;i++){
        if(board[i][col]=='.'){
            continue;
        }
        if(m.find(board[i][col])!=m.end()){
            return false;
        }
        m[board[i][col]]++;
    }
    return true;
}

bool validateBox(vector<vector<char>> &board,int row, int col, int eRow, int eCol){
    map<char,int> m;
    for(int i=row;i<=eRow;i++){
        for(int j=col;j<=eCol;j++){
            if(board[i][j]=='.'){
                continue;
            }
            if(m.find(board[i][j])!=m.end()){
                return false;
            }
            m[board[i][j]]++;
        }
    }
    return true;
}

bool isValidSudoku(vector<vector<char>>& board) {
    int res;
    for(int i=0;i<9;i++){
        res = validateRow(board,i);
        if(!res){
            return false;
        }
    }
    for(int j=0;j<9;j++){
        res = validateCol(board,j);
        if(!res){
            return false;
        }
    }
    for(int i=0;i<=6;i+=3){
        for(int j=0;j<=6;j+=3){
            res = validateBox(board,i,j,i+2,j+2);
            if(!res){
                return false;
            }
        }
    }

//         0 1 2 3 4 5 6 7 8 
//         1
//         2
//         3
//         4
//         5
//         6
//         7
//         8
    return true;

}

int main(){
  //call isValidSudoku();
}
