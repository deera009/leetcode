/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) {
 *         this.val = val;
 *     }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    private List<Integer> result = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {

        traverse(root);

        return result;
    }

    private void traverse(TreeNode node) {

        if (node == null) {
            return;
        }

        traverse(node.left);

        traverse(node.right);

        result.add(node.val);
    }
}