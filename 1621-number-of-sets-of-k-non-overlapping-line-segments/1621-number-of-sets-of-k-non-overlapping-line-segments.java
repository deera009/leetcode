class Solution {
    public int numberOfSets(int n, int k) {
        final long MOD = 1_000_000_007L;

        int N = n + k - 1;
        int R = 2 * k;

        long[][] dp = new long[N + 1][R + 1];

        // Base case
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        // Pascal's Triangle
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= R && j <= i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return (int) dp[N][R];
    }
}