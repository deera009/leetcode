class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the
        // previous index whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];

            int remainder = num % k;

            // Start a new subarray containing only num
            newDp[remainder]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRemainder = (r * remainder) % k;
                    newDp[newRemainder] += dp[r];
                }
            }

            // Add all subarrays ending at this position
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}