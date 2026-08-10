class Solution {

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode previous = null;

    public void recoverTree(TreeNode root) {

        inorder(root);

        // Swap the incorrect values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode node) {

        if (node == null) {
            return;
        }

        // Left
        inorder(node.left);

        // Current
        if (previous != null && previous.val > node.val) {

            // First violation
            if (first == null) {
                first = previous;
            }

            // Current node is the second incorrect node
            second = node;
        }

        previous = node;

        // Right
        inorder(node.right);
    }
}