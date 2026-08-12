class Solution {

    public Node connect(Node root) {

        if (root == null) {
            return null;
        }

        Node current = root;

        /*
         * Process one level at a time.
         */
        while (current != null) {

            /*
             * Dummy node represents the beginning
             * of the next level.
             */
            Node dummy = new Node(0);

            Node tail = dummy;

            /*
             * Traverse current level using next pointers.
             */
            while (current != null) {

                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }

                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }

                current = current.next;
            }

            /*
             * Move to the first node of the next level.
             */
            current = dummy.next;
        }

        return root;
    }
}