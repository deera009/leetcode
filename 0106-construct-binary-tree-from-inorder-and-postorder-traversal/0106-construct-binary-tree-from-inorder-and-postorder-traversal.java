import java.util.*;

class Solution {

    private int postIndex;
    private HashMap<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        inorderMap = new HashMap<>();
        postIndex = postorder.length - 1;

        // Store value -> index of inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder,
                           int left, int right) {

        if (left > right) {
            return null;
        }

        // Last element of postorder is the root
        int rootValue = postorder[postIndex--];

        TreeNode root = new TreeNode(rootValue);

        int rootIndex = inorderMap.get(rootValue);

        // IMPORTANT:
        // Build right subtree first because we are
        // traversing postorder backwards.
        root.right = build(inorder, postorder,
                           rootIndex + 1, right);

        root.left = build(inorder, postorder,
                          left, rootIndex - 1);

        return root;
    }
}