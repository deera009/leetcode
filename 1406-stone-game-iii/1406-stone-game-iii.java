class Solution {
    public String stoneGameIII(int[] stoneValue) {

        int n = stoneValue.length;

        int[] dp = new int[n + 1];

        // Base case:
        // No stones remaining => score difference is 0
        dp[n] = 0;

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {

            int sum = 0;

            // Try taking 1, 2, or 3 stones
            dp[i] = Integer.MIN_VALUE;

            for (int j = i; j < Math.min(i + 3, n); j++) {

                sum += stoneValue[j];

                dp[i] = Math.max(
                    dp[i],
                    sum - dp[j + 1]
                );
            }
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}