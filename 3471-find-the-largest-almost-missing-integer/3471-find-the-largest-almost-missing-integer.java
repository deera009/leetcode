class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] count = new int[51];
        int n = nums.length;

        for (int i = 0; i <= n - k; i++) {
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                seen[nums[j]] = true;
            }

            for (int num = 0; num <= 50; num++) {
                if (seen[num]) {
                    count[num]++;
                }
            }
        }

        int answer = -1;

        for (int num = 0; num <= 50; num++) {
            if (count[num] == 1) {
                answer = Math.max(answer, num);
            }
        }

        return answer;
    }
}