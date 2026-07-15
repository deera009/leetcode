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

    private int ans = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root.left, true, 1);   // first move is left
        dfs(root.right, false, 1); // first move is right
        return ans;
    }

    private void dfs(TreeNode node, boolean cameFromLeft, int length) {
        if (node == null)
            return;

        ans = Math.max(ans, length);

        if (cameFromLeft) {
            // Continue ZigZag: Left -> Right
            dfs(node.right, false, length + 1);

            // Restart from left child
            dfs(node.left, true, 1);
        } else {
            // Continue ZigZag: Right -> Left
            dfs(node.left, true, length + 1);

            // Restart from right child
            dfs(node.right, false, 1);
        }
    }
}