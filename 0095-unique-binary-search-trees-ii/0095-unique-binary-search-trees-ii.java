import java.util.*;

class Solution {

    public List<TreeNode> generateTrees(int n) {
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {

        List<TreeNode> result = new ArrayList<>();

        // Empty subtree
        if (start > end) {
            result.add(null);
            return result;
        }

        // Try every value as root
        for (int rootValue = start; rootValue <= end; rootValue++) {

            // Generate all possible left subtrees
            List<TreeNode> leftTrees =
                    buildTrees(start, rootValue - 1);

            // Generate all possible right subtrees
            List<TreeNode> rightTrees =
                    buildTrees(rootValue + 1, end);

            // Combine every left subtree with every right subtree
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {

                    TreeNode root = new TreeNode(rootValue);

                    root.left = left;
                    root.right = right;

                    result.add(root);
                }
            }
        }

        return result;
    }
}