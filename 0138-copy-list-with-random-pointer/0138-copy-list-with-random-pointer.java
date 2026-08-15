/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();

        Node current = head;

        // Create all cloned nodes
        while (current != null) {

            map.put(current, new Node(current.val));

            current = current.next;
        }

        current = head;

        // Connect next and random pointers
        while (current != null) {

            Node clone = map.get(current);

            clone.next = map.get(current.next);

            clone.random = map.get(current.random);

            current = current.next;
        }

        return map.get(head);
    }
}