class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;

        int[] ugly = new int[n];
        int[] index = new int[m];

        // 1 is the first super ugly number
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {

            long next = Long.MAX_VALUE;

            // Find the smallest possible next ugly number
            for (int j = 0; j < m; j++) {
                long candidate = (long) ugly[index[j]] * primes[j];
                next = Math.min(next, candidate);
            }

            ugly[i] = (int) next;

            // Move all pointers that produced this number
            for (int j = 0; j < m; j++) {
                long candidate = (long) ugly[index[j]] * primes[j];

                if (candidate == next) {
                    index[j]++;
                }
            }
        }

        return ugly[n - 1];
    }
}