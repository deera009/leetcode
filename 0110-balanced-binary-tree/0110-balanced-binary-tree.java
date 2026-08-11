class Solution {

    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode root) {

        // Empty tree has height 0
        if (root == null) {
            return 0;
        }

        // Check left subtree
        int leftHeight = height(root.left);

        if (leftHeight == -1) {
            return -1;
        }

        // Check right subtree
        int rightHeight = height(root.right);

        if (rightHeight == -1) {
            return -1;
        }

        // Current node is unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return height of current subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }
}