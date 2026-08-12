class Solution {
    public boolean predictTheWinner(int[] nums) {

        int n = nums.length;

        int[][] dp = new int[n][n];

        // Base case:
        // Only one number remains
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Build for increasing subarray lengths
        for (int len = 2; len <= n; len++) {

            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                int takeLeft = nums[left] - dp[left + 1][right];

                int takeRight = nums[right] - dp[left][right - 1];

                dp[left][right] = Math.max(takeLeft, takeRight);
            }
        }

        // Player 1 wins if score difference >= 0
        return dp[0][n - 1] >= 0;
    }
}