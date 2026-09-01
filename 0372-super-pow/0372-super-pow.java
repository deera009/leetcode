class Solution {

    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {

        a %= MOD;

        int result = 1;

        for (int digit : b) {

            // result = result^10 * a^digit
            result = (pow(result, 10) * pow(a, digit)) % MOD;
        }

        return result;
    }

    // Fast modular exponentiation
    private int pow(int base, int exponent) {

        int result = 1;

        base %= MOD;

        while (exponent > 0) {

            // If current bit is 1
            if ((exponent & 1) == 1) {
                result = (result * base) % MOD;
            }

            // Square the base
            base = (base * base) % MOD;

            // Divide exponent by 2
            exponent >>= 1;
        }

        return result;
    }
}