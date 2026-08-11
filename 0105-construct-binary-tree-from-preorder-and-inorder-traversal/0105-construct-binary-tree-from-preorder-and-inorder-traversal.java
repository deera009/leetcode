import java.util.*;

class Solution {

    private int preIndex;
    private HashMap<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        preIndex = 0;
        inorderMap = new HashMap<>();

        // Store value -> index in inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {

        // No elements in this subtree
        if (left > right) {
            return null;
        }

        // First element in preorder is the root
        int rootValue = preorder[preIndex++];

        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int rootIndex = inorderMap.get(rootValue);

        // Build left subtree
        root.left = build(preorder, left, rootIndex - 1);

        // Build right subtree
        root.right = build(preorder, rootIndex + 1, right);

        return root;
    }
}