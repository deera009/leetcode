import java.util.*;

class Solution {

    private static final int INF = 1_000_000_000;

    // Prime factor contribution of digits 0..9
    //       2  3  5  7
    private static final int[][] FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    private int A, B, C, D;
    private int[] dp;

    public String smallestNumber(String num, long t) {

        int[] need = factorize(t);

        // t has a prime factor other than 2,3,5,7
        if (need == null) {
            return "-1";
        }

        A = need[0];
        B = need[1];
        C = need[2];
        D = need[3];

        int states = (A + 1)
                   * (B + 1)
                   * (C + 1)
                   * (D + 1);

        dp = new int[states];
        Arrays.fill(dp, INF);

        buildDP();

        int minDigits = get(A, B, C, D);

        if (minDigits == INF) {
            return "-1";
        }

        /*
         * First try the same length.
         */
        if (num.length() >= minDigits) {

            String result = buildSameLength(num);

            if (result != null) {
                return result;
            }
        }

        /*
         * No valid answer with the same number of digits.
         *
         * Therefore we need at least one additional digit.
         */
        int length = Math.max(
            num.length() + 1,
            minDigits
        );

        return buildSmallest(
            length,
            A,
            B,
            C,
            D
        );
    }

    // ============================================================
    // FACTORIZATION
    // ============================================================

    private int[] factorize(long t) {

        int[] need = new int[4];

        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {

            while (t % primes[i] == 0) {
                need[i]++;
                t /= primes[i];
            }
        }

        // Impossible to obtain this prime using digits 1..9.
        if (t != 1) {
            return null;
        }

        return need;
    }

    // ============================================================
    // DP
    // ============================================================

    /*
     * dp[a,b,c,d] =
     * minimum number of digits required to obtain
     * at least:
     *
     * 2^a * 3^b * 5^c * 7^d
     */
    private void buildDP() {

        dp[getIndex(0, 0, 0, 0)] = 0;

        for (int a = 0; a <= A; a++) {
            for (int b = 0; b <= B; b++) {
                for (int c = 0; c <= C; c++) {
                    for (int d = 0; d <= D; d++) {

                        int current = getIndex(a, b, c, d);

                        if (dp[current] == INF) {
                            continue;
                        }

                        for (int digit = 2; digit <= 9; digit++) {

                            int na = Math.min(
                                A,
                                a + FACTORS[digit][0]
                            );

                            int nb = Math.min(
                                B,
                                b + FACTORS[digit][1]
                            );

                            int nc = Math.min(
                                C,
                                c + FACTORS[digit][2]
                            );

                            int nd = Math.min(
                                D,
                                d + FACTORS[digit][3]
                            );

                            int next = getIndex(
                                na, nb, nc, nd
                            );

                            dp[next] = Math.min(
                                dp[next],
                                dp[current] + 1
                            );
                        }
                    }
                }
            }
        }
    }

    // ============================================================
    // SMALLEST NUMBER OF A FIXED LENGTH
    // ============================================================

    private String buildSmallest(
        int length,
        int a,
        int b,
        int c,
        int d
    ) {

        StringBuilder result =
            new StringBuilder(length);

        for (int pos = 0; pos < length; pos++) {

            int remaining = length - pos - 1;

            for (int digit = 1; digit <= 9; digit++) {

                int[] next = removeFactors(
                    a, b, c, d, digit
                );

                if (get(
                    next[0],
                    next[1],
                    next[2],
                    next[3]
                ) <= remaining) {

                    result.append(
                        (char) ('0' + digit)
                    );

                    a = next[0];
                    b = next[1];
                    c = next[2];
                    d = next[3];

                    break;
                }
            }
        }

        return result.toString();
    }

    // ============================================================
    // SAME LENGTH: FIND SMALLEST >= NUM
    // ============================================================

    private String buildSameLength(String num) {

        int n = num.length();

        /*
         * remaining requirement after consuming
         * num[0 ... i-1].
         */
        int[] remA = new int[n + 1];
        int[] remB = new int[n + 1];
        int[] remC = new int[n + 1];
        int[] remD = new int[n + 1];

        remA[0] = A;
        remB[0] = B;
        remC[0] = C;
        remD[0] = D;

        /*
         * zeroPrefix[i] =
         * whether num[0 ... i-1] contains zero.
         */
        boolean[] zeroPrefix = new boolean[n + 1];

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            remA[i + 1] = Math.max(
                0,
                remA[i] - FACTORS[digit][0]
            );

            remB[i + 1] = Math.max(
                0,
                remB[i] - FACTORS[digit][1]
            );

            remC[i + 1] = Math.max(
                0,
                remC[i] - FACTORS[digit][2]
            );

            remD[i + 1] = Math.max(
                0,
                remD[i] - FACTORS[digit][3]
            );

            zeroPrefix[i + 1] =
                zeroPrefix[i] || digit == 0;
        }

        // --------------------------------------------------------
        // Case 1: num itself is valid.
        // --------------------------------------------------------

        if (!zeroPrefix[n] &&
            remA[n] == 0 &&
            remB[n] == 0 &&
            remC[n] == 0 &&
            remD[n] == 0) {

            return num;
        }

        // --------------------------------------------------------
        // Case 2: Increase one digit.
        //
        // We want the RIGHTMOST possible pivot because that
        // produces the smallest lexicographical number.
        //
        // BUT:
        // the unchanged prefix must contain no zero.
        // --------------------------------------------------------

        for (int pivot = n - 1; pivot >= 0; pivot--) {

            /*
             * num[0 ... pivot-1] must be zero-free.
             *
             * If it contains zero, this pivot is invalid.
             */
            if (zeroPrefix[pivot]) {
                continue;
            }

            int currentDigit =
                num.charAt(pivot) - '0';

            /*
             * Prefix requirement after keeping
             * num[0 ... pivot-1].
             */
            int a = remA[pivot];
            int b = remB[pivot];
            int c = remC[pivot];
            int d = remD[pivot];

            /*
             * Try the smallest digit greater than
             * the original digit.
             */
            for (
                int digit = currentDigit + 1;
                digit <= 9;
                digit++
            ) {

                /*
                 * digit cannot be zero anyway because
                 * digit > currentDigit >= 0.
                 */
                int[] next = removeFactors(
                    a, b, c, d, digit
                );

                int remaining =
                    n - pivot - 1;

                /*
                 * Can the suffix satisfy the remaining
                 * prime-factor requirement?
                 */
                if (get(
                    next[0],
                    next[1],
                    next[2],
                    next[3]
                ) <= remaining) {

                    StringBuilder answer =
                        new StringBuilder(n);

                    /*
                     * Copy zero-free prefix.
                     */
                    answer.append(
                        num,
                        0,
                        pivot
                    );

                    /*
                     * Increased digit.
                     */
                    answer.append(
                        (char) ('0' + digit)
                    );

                    /*
                     * Smallest possible zero-free suffix.
                     */
                    String suffix = buildSmallest(
                        remaining,
                        next[0],
                        next[1],
                        next[2],
                        next[3]
                    );

                    answer.append(suffix);

                    return answer.toString();
                }
            }
        }

        return null;
    }

    // ============================================================
    // REMOVE FACTORS
    // ============================================================

    private int[] removeFactors(
        int a,
        int b,
        int c,
        int d,
        int digit
    ) {

        return new int[] {
            Math.max(
                0,
                a - FACTORS[digit][0]
            ),

            Math.max(
                0,
                b - FACTORS[digit][1]
            ),

            Math.max(
                0,
                c - FACTORS[digit][2]
            ),

            Math.max(
                0,
                d - FACTORS[digit][3]
            )
        };
    }

    // ============================================================
    // DP INDEX
    // ============================================================

    private int get(int a, int b, int c, int d) {
        return dp[getIndex(a, b, c, d)];
    }

    private int getIndex(
        int a,
        int b,
        int c,
        int d
    ) {

        return (((a * (B + 1) + b)
                * (C + 1) + c)
                * (D + 1) + d);
    }
}