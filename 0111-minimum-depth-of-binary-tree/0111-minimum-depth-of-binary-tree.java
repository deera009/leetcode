class Solution {

    public int minDepth(TreeNode root) {

        // Empty tree
        if (root == null) {
            return 0;
        }

        // Leaf node
        if (root.left == null && root.right == null) {
            return 1;
        }

        // Only right child exists
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        // Only left child exists
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        // Both children exist
        return Math.min(
            minDepth(root.left),
            minDepth(root.right)
        ) + 1;
    }
}