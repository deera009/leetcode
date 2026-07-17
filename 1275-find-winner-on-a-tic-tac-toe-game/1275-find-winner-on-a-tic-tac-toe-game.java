class Solution {
    public String tictactoe(int[][] moves) {
        char[][] board = new char[3][3];

        for (int i = 0; i < moves.length; i++) {
            int r = moves[i][0];
            int c = moves[i][1];

            board[r][c] = (i % 2 == 0) ? 'X' : 'O';
        }

        if (win(board, 'X')) return "A";
        if (win(board, 'O')) return "B";

        return moves.length == 9 ? "Draw" : "Pending";
    }

    private boolean win(char[][] board, char ch) {
        // Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == ch &&
                board[i][1] == ch &&
                board[i][2] == ch)
                return true;
        }

        // Columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == ch &&
                board[1][j] == ch &&
                board[2][j] == ch)
                return true;
        }

        // Main diagonal
        if (board[0][0] == ch &&
            board[1][1] == ch &&
            board[2][2] == ch)
            return true;

        // Anti-diagonal
        if (board[0][2] == ch &&
            board[1][1] == ch &&
            board[2][0] == ch)
            return true;

        return false;
    }
}