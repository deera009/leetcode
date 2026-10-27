
class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '#');
            grid[i] = new String(row);
        }

        char[][] cells = new char[m][n];

        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(cells[i], '#');
        }

        for (int j = 0; j < n; j++) {
            cells[0][j] = '.';
        }

        for (int i = 1; i < m; i++) {
            cells[i][n - 1] = '.';
        }

        for (int i = 0; i < m; i++) {
            grid[i] = new String(cells[i]);
        }

        return grid;
    }
}
