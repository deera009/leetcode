class Solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {

        // Empty tree
        if (root == null) {
            return false;
        }

        // Leaf node
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Remaining sum after using current node
        int remainingSum = targetSum - root.val;

        // Check left or right subtree
        return hasPathSum(root.left, remainingSum)
                || hasPathSum(root.right, remainingSum);
    }
}