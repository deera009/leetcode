import java.util.*;

class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, path, result);

        return result;
    }

    private void dfs(TreeNode root,
                     int targetSum,
                     List<Integer> path,
                     List<List<Integer>> result) {

        // Empty node
        if (root == null) {
            return;
        }

        // Add current node to path
        path.add(root.val);

        // Check if current node is a leaf
        if (root.left == null && root.right == null) {

            if (targetSum == root.val) {
                result.add(new ArrayList<>(path));
            }

            // Backtrack
            path.remove(path.size() - 1);
            return;
        }

        // Remaining sum
        int remainingSum = targetSum - root.val;

        // Explore left subtree
        dfs(root.left, remainingSum, path, result);

        // Explore right subtree
        dfs(root.right, remainingSum, path, result);

        // Backtrack
        path.remove(path.size() - 1);
    }
}