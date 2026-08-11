class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {

        // No elements
        if (left > right) {
            return null;
        }

        // Find middle element
        int mid = left + (right - left) / 2;

        // Middle element becomes root
        TreeNode root = new TreeNode(nums[mid]);

        // Build left subtree
        root.left = build(nums, left, mid - 1);

        // Build right subtree
        root.right = build(nums, mid + 1, right);

        return root;
    }
}