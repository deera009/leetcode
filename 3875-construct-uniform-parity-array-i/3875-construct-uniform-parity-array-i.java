class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean hasOdd = false;

        for (int num : nums1) {
            if (num % 2 != 0) {
                hasOdd = true;
                break;
            }
        }

        return true;
    }
}