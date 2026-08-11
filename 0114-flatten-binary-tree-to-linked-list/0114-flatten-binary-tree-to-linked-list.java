class Solution {

    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }

        // Flatten left and right subtrees
        flatten(root.left);
        flatten(root.right);

        // Store the flattened right subtree
        TreeNode rightSubtree = root.right;

        // Move left subtree to right
        root.right = root.left;
        root.left = null;

        // Find the end of the new right subtree
        TreeNode current = root;

        while (current.right != null) {
            current = current.right;
        }

        // Attach original right subtree
        current.right = rightSubtree;
    }
}