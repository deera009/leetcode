import java.util.*;

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>());

        return ans;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> curr) {

        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) {
                continue;
            }

            // Skip duplicate numbers
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            curr.add(nums[i]);

            backtrack(nums, used, curr);

            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}