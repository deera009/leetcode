class Solution {

    public TreeNode sortedListToBST(ListNode head) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Find the middle node
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 'slow' is the middle node
        TreeNode root = new TreeNode(slow.val);

        // If there is only one node
        if (prev == null) {
            return root;
        }

        // Disconnect left half from middle
        prev.next = null;

        // Build left subtree
        root.left = sortedListToBST(head);

        // Build right subtree
        root.right = sortedListToBST(slow.next);

        return root;
    }
}