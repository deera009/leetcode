class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count how many times each digit appears
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // Check every 3-digit number
        for (int num = 100; num <= 999; num++) {

            // Must be even
            if (num % 2 != 0) {
                continue;
            }

            int x = num;

            int ones = x % 10;
            x /= 10;

            int tens = x % 10;
            x /= 10;

            int hundreds = x;

            // Temporarily use the digits
            freq[ones]--;
            freq[tens]--;
            freq[hundreds]--;

            // If all frequencies remain non-negative,
            // the number can be formed
            if (freq[ones] >= 0 &&
                freq[tens] >= 0 &&
                freq[hundreds] >= 0) {
                count++;
            }

            // Restore frequencies
            freq[ones]++;
            freq[tens]++;
            freq[hundreds]++;
        }

        return count;
    }
}