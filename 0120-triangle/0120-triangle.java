import java.util.*;

class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        /*
         * dp[j] represents the minimum path sum
         * from the current position to the bottom.
         *
         * Start with the last row.
         */
        int[] dp = new int[n];

        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        /*
         * Process rows from bottom to top.
         */
        for (int i = n - 2; i >= 0; i--) {

            for (int j = 0; j <= i; j++) {

                dp[j] = triangle.get(i).get(j)
                        + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }
}