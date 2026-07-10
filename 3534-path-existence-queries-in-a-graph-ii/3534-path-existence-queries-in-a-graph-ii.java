class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        int[] comp = new int[n];

        int cid = 0;
        pos[arr[0][1]] = 0;
        comp[0] = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i][0] - arr[i - 1][0] > maxDiff)
                cid++;
            pos[arr[i][1]] = i;
            comp[i] = cid;
        }

        int[] next = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j + 1 < n && arr[j + 1][0] - arr[i][0] <= maxDiff)
                j++;
            next[i] = j;
        }

        int LOG = 18;
        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++)
            up[0][i] = next[i];

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int u = queries[qi][0];
            int v = queries[qi][1];

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            int l = pos[u];
            int r = pos[v];

            if (l > r) {
                int t = l;
                l = r;
                r = t;
            }

            if (comp[l] != comp[r]) {
                ans[qi] = -1;
                continue;
            }

            int cur = l;
            int res = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < r) {
                    cur = up[k][cur];
                    res += 1 << k;
                }
            }

            ans[qi] = res + 1;
        }

        return ans;
    }
}