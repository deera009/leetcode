class Solution {

    public int getMoneyAmount(int n) {

        // dp[left][right] =
        // minimum cost to guarantee a win
        // when the number is between left and right.
        int[][] dp = new int[n + 2][n + 2];

        // length = size of the current range
        for (int length = 2; length <= n; length++) {

            for (int left = 1;
                 left + length - 1 <= n;
                 left++) {

                int right = left + length - 1;

                // Start with a very large cost.
                dp[left][right] = Integer.MAX_VALUE;

                // Try every possible first guess.
                for (int x = left; x <= right; x++) {

                    int leftCost = dp[left][x - 1];
                    int rightCost = dp[x + 1][right];

                    // Worst-case cost after guessing x.
                    int cost = x + Math.max(leftCost, rightCost);

                    // Choose the guess with minimum
                    // worst-case cost.
                    dp[left][right] =
                            Math.min(dp[left][right], cost);
                }
            }
        }

        return dp[1][n];
    }
}