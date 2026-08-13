class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int best;
        int length;
    }

    private Node[] tree;
    private char[] s;
    private int n;

    private Node merge(Node left, Node right) {
        Node node = new Node();

        node.length = left.length + right.length;

        node.leftChar = left.leftChar;
        node.rightChar = right.rightChar;

        node.prefix = left.prefix;

        if (left.prefix == left.length &&
                left.rightChar == right.leftChar) {
            node.prefix = left.length + right.prefix;
        }

        node.suffix = right.suffix;

        if (right.suffix == right.length &&
                left.rightChar == right.leftChar) {
            node.suffix = right.length + left.suffix;
        }

        node.best = Math.max(left.best, right.best);

        if (left.rightChar == right.leftChar) {
            node.best = Math.max(
                    node.best,
                    left.suffix + right.prefix
            );
        }

        return node;
    }

    private void build(int idx, int l, int r) {
        tree[idx] = new Node();

        if (l == r) {
            tree[idx].leftChar = s[l];
            tree[idx].rightChar = s[l];

            tree[idx].prefix = 1;
            tree[idx].suffix = 1;
            tree[idx].best = 1;
            tree[idx].length = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(
                tree[idx * 2],
                tree[idx * 2 + 1]
        );
    }

    private void update(int idx, int l, int r, int pos, char ch) {

        if (l == r) {
            s[pos] = ch;

            tree[idx].leftChar = ch;
            tree[idx].rightChar = ch;

            tree[idx].prefix = 1;
            tree[idx].suffix = 1;
            tree[idx].best = 1;
            tree[idx].length = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (pos <= mid) {
            update(idx * 2, l, mid, pos, ch);
        } else {
            update(idx * 2 + 1, mid + 1, r, pos, ch);
        }

        tree[idx] = merge(
                tree[idx * 2],
                tree[idx * 2 + 1]
        );
    }

    public int[] longestRepeating(String str,
                                  String queryCharacters,
                                  int[] queryIndices) {

        s = str.toCharArray();
        n = s.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            update(
                    1,
                    0,
                    n - 1,
                    queryIndices[i],
                    queryCharacters.charAt(i)
            );

            ans[i] = tree[1].best;
        }

        return ans;
    }
}