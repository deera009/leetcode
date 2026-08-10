import java.util.*;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        backtrack(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(
            int[] nums,
            int start,
            List<Integer> current,
            List<List<Integer>> result) {

        // Every state represents a valid subset
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            // Skip duplicate choices at the same level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Choose
            current.add(nums[i]);

            // Explore
            backtrack(nums, i + 1, current, result);

            // Undo choice
            current.remove(current.size() - 1);
        }
    }
}