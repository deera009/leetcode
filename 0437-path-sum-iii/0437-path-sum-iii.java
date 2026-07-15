/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> map = new HashMap<>();

        // Empty path has prefix sum 0
        map.put(0L, 1);

        return dfs(root, 0L, targetSum, map);
    }

    private int dfs(TreeNode node, long currSum, int target,
                    HashMap<Long, Integer> map) {

        if (node == null)
            return 0;

        currSum += node.val;

        int count = map.getOrDefault(currSum - target, 0);

        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        count += dfs(node.left, currSum, target, map);
        count += dfs(node.right, currSum, target, map);

        // Backtracking
        map.put(currSum, map.get(currSum) - 1);

        return count;
    }
}