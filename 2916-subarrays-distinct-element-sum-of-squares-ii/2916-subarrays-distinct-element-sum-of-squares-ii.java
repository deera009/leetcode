
class Solution {
    private static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] sum = new long[4 * n];
        long[] square = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        long answer = 0;

        for (int i = 0; i < n; i++) {
            int value = nums[i];
            int previous = last[value];

            update(1, 0, n - 1, previous, i, 1,
                   sum, square, lazy);

            answer = (answer + square[1]) % MOD;
            last[value] = i + 1;
        }

        return (int) answer;
    }

    private void update(int node, int start, int end,
                        int left, int right, long value,
                        long[] sum, long[] square, long[] lazy) {
        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, start, end, value, sum, square, lazy);
            return;
        }

        push(node, start, end, sum, square, lazy);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right, value,
               sum, square, lazy);
        update(node * 2 + 1, mid + 1, end, left, right, value,
               sum, square, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int start, int end, long value,
                       long[] sum, long[] square, long[] lazy) {
        long length = end - start + 1L;
        long oldSum = sum[node];

        square[node] = (square[node]
                + 2L * value % MOD * oldSum
                + length * value % MOD * value) % MOD;

        sum[node] = (oldSum + length * value) % MOD;
        lazy[node] = (lazy[node] + value) % MOD;
    }

    private void push(int node, int start, int end,
                      long[] sum, long[] square, long[] lazy) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        apply(node * 2, start, mid, lazy[node],
              sum, square, lazy);
        apply(node * 2 + 1, mid + 1, end, lazy[node],
              sum, square, lazy);

        lazy[node] = 0;
    }
}
