
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean sortable = true;
            int previousMax = -1;

            for (int start = 0; start < n && sortable; start += k) {
                int descents = 0;
                int min = nums[start];
                int max = nums[start];

                for (int i = start; i < start + k; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);

                    int next = (i + 1 == start + k)
                            ? start
                            : i + 1;

                    if (nums[i] > nums[next]) {
                        descents++;
                    }
                }

                if (descents > 1 ||
                    (start > 0 && previousMax > min)) {
                    sortable = false;
                }

                previousMax = max;
            }

            if (sortable) {
                answer += k;
            }
        }

        return answer;
    }
}
