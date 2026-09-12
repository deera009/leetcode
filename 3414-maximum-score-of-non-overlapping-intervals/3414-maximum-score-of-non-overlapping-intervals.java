import java.util.*;

class Solution {

    int n;
    int[][] intervals;
    int[][] next;
    long[][] dpScore;
    List<Integer>[][] dpList;

    public int[] maximumWeight(List<List<Integer>> intervalsList) {

        n = intervalsList.size();
        intervals = new int[n][4];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = intervalsList.get(i).get(0); // left
            intervals[i][1] = intervalsList.get(i).get(1); // right
            intervals[i][2] = intervalsList.get(i).get(2); // weight
            intervals[i][3] = i;                           // original index
        }

        // Sort by starting point
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Find next non-overlapping interval for every interval
        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
        }

        next = new int[n][1];

        for (int i = 0; i < n; i++) {
            int target = intervals[i][1] + 1;
            next[i][0] = lowerBound(starts, target);
        }

        dpScore = new long[n + 1][5];
        dpList = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dpList[i][k] = new ArrayList<>();
            }
        }

        // DP
        solve(0, 4);

        List<Integer> answer = dpList[0][4];

        // Return indices in increasing order
        Collections.sort(answer);

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int lowerBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long solve(int i, int k) {

        if (i == n || k == 0) {
            return 0;
        }

        if (dpScore[i][k] != 0) {
            return dpScore[i][k];
        }

        // Option 1: Skip current interval
        long skipScore = solve(i + 1, k);
        List<Integer> skipList = new ArrayList<>(dpList[i + 1][k]);

        // Option 2: Take current interval
        int j = next[i][0];

        long takeScore = intervals[i][2] + solve(j, k - 1);

        List<Integer> takeList = new ArrayList<>();
        takeList.add(intervals[i][3]);
        takeList.addAll(dpList[j][k - 1]);

        // Choose better solution
        if (takeScore > skipScore) {

            dpScore[i][k] = takeScore;
            dpList[i][k] = takeList;

        } else if (takeScore < skipScore) {

            dpScore[i][k] = skipScore;
            dpList[i][k] = skipList;

        } else {

            // Same score -> lexicographically smaller indices
            Collections.sort(takeList);
            Collections.sort(skipList);

            if (lexicographicallySmaller(takeList, skipList)) {
                dpList[i][k] = takeList;
            } else {
                dpList[i][k] = skipList;
            }

            dpScore[i][k] = takeScore;
        }

        return dpScore[i][k];
    }

    private boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}