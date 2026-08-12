class Solution {

    public Node connect(Node root) {

        if (root == null) {
            return null;
        }

        Node leftmost = root;

        /*
         * Process one level at a time.
         */
        while (leftmost.left != null) {

            Node current = leftmost;

            /*
             * Traverse the current level using next pointers.
             */
            while (current != null) {

                // Connect left child -> right child
                current.left.next = current.right;

                /*
                 * Connect current node's right child
                 * to the next parent's left child.
                 */
                if (current.next != null) {
                    current.right.next = current.next.left;
                }

                current = current.next;
            }

            /*
             * Move to the next level.
             */
            leftmost = leftmost.left;
        }

        return root;
    }
}