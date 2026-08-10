class Solution {

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {

        if (node == null) {
            return true;
        }

        // Node must be strictly inside the allowed range
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree: values must be smaller
        boolean leftValid =
                validate(node.left, min, node.val);

        // Right subtree: values must be greater
        boolean rightValid =
                validate(node.right, node.val, max);

        return leftValid && rightValid;
    }
}