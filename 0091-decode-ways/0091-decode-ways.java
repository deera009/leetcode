class Solution {
    public int numDecodings(String s) {

        int n = s.length();

        int[] dp = new int[n + 1];

        // Empty string has one valid way
        dp[0] = 1;

        // First character
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        }

        for (int i = 2; i <= n; i++) {

            // Take one digit
            char current = s.charAt(i - 1);

            if (current != '0') {
                dp[i] += dp[i - 1];
            }

            // Take two digits
            int twoDigit =
                    (s.charAt(i - 2) - '0') * 10
                    + (s.charAt(i - 1) - '0');

            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}