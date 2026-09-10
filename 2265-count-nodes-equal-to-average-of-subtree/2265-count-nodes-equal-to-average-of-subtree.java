class Solution {

    int answer = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return answer;
    }

    // Returns [sum, count]
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Get information from left subtree
        int[] left = dfs(node.left);

        // Get information from right subtree
        int[] right = dfs(node.right);

        // Calculate current subtree information
        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        // Integer division performs floor division here
        int average = sum / count;

        if (node.val == average) {
            answer++;
        }

        return new int[]{sum, count};
    }
}