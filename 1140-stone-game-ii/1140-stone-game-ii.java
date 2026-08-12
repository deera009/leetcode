class Solution {

    private int[][] dp;
    private int[] suffix;
    private int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        suffix = new int[n + 1];

        /*
         * suffix[i] = total stones from i to n-1
         */
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        /*
         * dp[i][m] =
         * maximum stones the current player can obtain
         * starting from index i with M = m.
         */
        dp = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return solve(0, 1);
    }

    private int solve(int i, int m) {

        /*
         * All remaining piles can be taken.
         */
        if (i + 2 * m >= n) {
            return suffix[i];
        }

        if (dp[i][m] != -1) {
            return dp[i][m];
        }

        int best = 0;

        /*
         * Try taking X piles.
         */
        for (int x = 1; x <= 2 * m; x++) {

            /*
             * Stones remaining after taking x piles.
             *
             * Opponent will play optimally from there.
             */
            int opponent = solve(
                i + x,
                Math.max(m, x)
            );

            int current =
                suffix[i] - opponent;

            best = Math.max(best, current);
        }

        return dp[i][m] = best;
    }
}