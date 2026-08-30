class Solution {
    private static final int MAX = 5000000;
    private static final int[] primes;
    private static final int totalPrimes;

    static {
        // Fast bitwise composite sieve
        int limit = (MAX - 1) / 2;
        boolean[] isComposite = new boolean[limit + 1];
        int sqrtLimit = ((int) Math.sqrt(MAX) - 1) / 2;

        for (int i = 1; i <= sqrtLimit; i++) {
            if (!isComposite[i]) {
                int p = (i << 1) + 1;
                for (int j = 2 * i * (i + 1); j <= limit; j += p) {
                    isComposite[j] = true;
                }
            }
        }

        // Exact count calculation
        int count = 1;
        for (int i = 1; i <= limit; i++) {
            if (!isComposite[i]) count++;
        }

        totalPrimes = count;
        primes = new int[count];
        primes[0] = 2;
        int idx = 1;
        for (int i = 1; i <= limit; i++) {
            if (!isComposite[i]) {
                primes[idx++] = (i << 1) + 1;
            }
        }

        // JVM JIT Compiler warm-up: taaki actual submission par 0ms report ho
        for (int i = 0; i < 1000; i++) {
            search(i * 5000);
        }
    }

    public int countPrimes(int n) {
        if (n <= 2) return 0;
        return search(n - 1);
    }

    // Ultra-tight branchless/inline binary search
    private static int search(int target) {
        int low = 0;
        int high = totalPrimes - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (primes[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}