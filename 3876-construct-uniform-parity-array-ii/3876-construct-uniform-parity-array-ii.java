class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean hasOdd = false;
        boolean hasEven = false;

        for (int num : nums1) {
            min = Math.min(min, num);

            if (num % 2 == 0) {
                hasEven = true;
            } else {
                hasOdd = true;
            }
        }

        // All elements already have the same parity
        if (!hasOdd || !hasEven) {
            return true;
        }

        // Mixed parity:
        // If minimum is odd, make all elements odd.
        // If minimum is even, impossible to make the smallest odd element even.
        return min % 2 == 1;
    }
}