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

    private int maxSum = Integer.MIN_VALUE;

    private int dfs(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = Math.max(0, dfs(root.left));

        int right = Math.max(0, dfs(root.right));

        int currentPath = root.val + left + right;

        maxSum = Math.max(maxSum, currentPath);

        return root.val + Math.max(left, right);
    }

    public int maxPathSum(TreeNode root) {

        dfs(root);

        return maxSum;
    }
}