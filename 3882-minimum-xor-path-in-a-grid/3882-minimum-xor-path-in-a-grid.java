
import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        BitSet[][] dp = new BitSet[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new BitSet(1024);
            }
        }

        dp[0][0].set(grid[0][0]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if (i > 0) {
                    addXors(dp[i][j], dp[i - 1][j], grid[i][j]);
                }

                if (j > 0) {
                    addXors(dp[i][j], dp[i][j - 1], grid[i][j]);
                }
            }
        }

        return dp[m - 1][n - 1].nextSetBit(0);
    }

    // Adds all possible XOR values from the previous cell.
    private void addXors(BitSet current, BitSet previous, int value) {
        for (int x = previous.nextSetBit(0);
             x >= 0;
             x = previous.nextSetBit(x + 1)) {
            current.set(x ^ value);
        }
    }
}
