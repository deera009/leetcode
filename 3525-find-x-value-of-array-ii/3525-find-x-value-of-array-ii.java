class Solution {

    static class Node {
        int product;
        long[] count;

        Node(int k) {
            count = new long[k];
        }
    }

    int n;
    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // Query range [start, n - 1]
            Node result = query(1, 0, n - 1, start, n - 1);

            answer[q] = (int) result.count[x];
        }

        return answer;
    }

    // ------------------------------------------------------------
    // Build
    // ------------------------------------------------------------

    void build(int node, int left, int right, int[] nums) {

        if (left == right) {
            tree[node] = createLeaf(nums[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ------------------------------------------------------------
    // Create leaf
    // ------------------------------------------------------------

    Node createLeaf(int value) {

        Node node = new Node(k);

        int rem = value % k;

        node.product = rem;
        node.count[rem] = 1;

        return node;
    }

    // ------------------------------------------------------------
    // Merge two segments: left + right
    // ------------------------------------------------------------

    Node merge(Node left, Node right) {

        Node result = new Node(k);

        // Product of the complete segment
        result.product = (left.product * right.product) % k;

        // Prefixes completely inside the left segment
        for (int r = 0; r < k; r++) {
            result.count[r] += left.count[r];
        }

        // Prefixes that use all of left + a prefix of right
        for (int r = 0; r < k; r++) {

            int newRemainder = (left.product * r) % k;

            result.count[newRemainder] += right.count[r];
        }

        return result;
    }

    // ------------------------------------------------------------
    // Point update
    // ------------------------------------------------------------

    void update(int node, int left, int right, int index, int value) {

        if (left == right) {
            tree[node] = createLeaf(value);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ------------------------------------------------------------
    // Range query
    // ------------------------------------------------------------

    Node query(int node, int left, int right, int ql, int qr) {

        // Complete overlap
        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        // Query only right part
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        // Query only left part
        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        // Query both parts
        Node leftResult = query(node * 2, left, mid, ql, qr);
        Node rightResult = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(leftResult, rightResult);
    }
}