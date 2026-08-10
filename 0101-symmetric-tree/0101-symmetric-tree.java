import java.util.*;

class Solution {

    public boolean isSymmetric(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {

            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // Both null
            if (left == null && right == null) {
                continue;
            }

            // One null or values differ
            if (left == null ||
                right == null ||
                left.val != right.val) {

                return false;
            }

            // Outer pair
            queue.offer(left.left);
            queue.offer(right.right);

            // Inner pair
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }
}