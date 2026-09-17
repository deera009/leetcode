class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        // best[i] = shortest target-sum subarray
        // completely contained in arr[0...i]
        int[] best = new int[n];

        int INF = n + 1;

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Remove elements while sum is greater than target
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            // A target-sum subarray [left ... right] is found
            if (sum == target) {
                int len = right - left + 1;

                // Check whether there is a previous
                // non-overlapping target-sum subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        best[left - 1] + len
                    );
                }

                // Store the shortest target-sum subarray
                // ending at or before 'right'
                if (right == 0) {
                    best[right] = len;
                } else {
                    best[right] = Math.min(best[right - 1], len);
                }
            } else {
                // No new subarray ending at right
                if (right > 0) {
                    best[right] = best[right - 1];
                }
            }
        }

        return answer == INF ? -1 : answer;
    }
}