class Solution {

    private long[] prefix;
    private Integer[][] memo;

    private long getSum(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }

    private int dp(int left, int right) {

        if (left == right) {
            return 0;
        }

        if (memo[left][right] != null) {
            return memo[left][right];
        }

        int ans = 0;

        for (int k = left; k < right; k++) {

            long leftSum = getSum(left, k);
            long rightSum = getSum(k + 1, right);

            if (leftSum < rightSum) {

                ans = Math.max(
                        ans,
                        (int) leftSum + dp(left, k)
                );

            } else if (leftSum > rightSum) {

                ans = Math.max(
                        ans,
                        (int) rightSum + dp(k + 1, right)
                );

            } else {

                ans = Math.max(
                        ans,
                        (int) leftSum
                                + Math.max(
                                        dp(left, k),
                                        dp(k + 1, right)
                                )
                );
            }
        }

        memo[left][right] = ans;

        return ans;
    }

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        memo = new Integer[n][n];

        return dp(0, n - 1);
    }
}