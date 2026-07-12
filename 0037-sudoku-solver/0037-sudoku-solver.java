class Solution {
     boolean [][]rows=new boolean[9][9];
     boolean [][]columns=new boolean[9][9];
     boolean [][]boxes=new boolean[9][9];
    public void solveSudoku(char[][] board) {
     for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            if(board[i][j]!='.'){
                int num=board[i][j]-'1';
                int boxIndex=(i/3)*3+(j/3);
                rows[i][num]=true;
                columns[j][num]=true;
                boxes[boxIndex][num]=true;
            }}}
     backtrack(board,0,0);
    }
    private boolean backtrack(char[][]board,int row,int col){
        if(col==9){
            col=0;
            row++;
        }
        if(row==9){ return true;}
        if(board[row][col]!='.'){
             return backtrack(board,row,col+1);
        }
        int boxIndex=(row/3)*3+(col/3);
        for(int num=0;num<9;num++){
            if(!rows[row][num]&& !columns[col][num]&& !boxes[boxIndex][num]){
                board[row][col]=(char) (num+'1');
                rows[row][num]=true;
                columns[col][num]=true;
                boxes[boxIndex][num]=true;
                if(backtrack(board,row,col+1)){
                    return true;
                }
                board[row][col]='.';
                rows[row][num]=false;
                columns[col][num]=false;
                boxes[boxIndex][num]=false;
            }}
        return false;
    }}