import java.util.*;

class Solution {
    public String largestNumber(int[] nums) {

        String[] arr = new String[nums.length];

        // Convert integers to strings
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        // Custom sorting
        Arrays.sort(arr, (a, b) -> {
            return (b + a).compareTo(a + b);
        });

        // If the largest number is "0",
        // all numbers are zero.
        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        for (String s : arr) {
            result.append(s);
        }

        return result.toString();
    }
}