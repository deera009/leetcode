class Solution {

    public int maximumGap(int[] nums) {

        int n = nums.length;

        if (n < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) {
            return 0;
        }

        int bucketSize = (int) Math.ceil((double) (max - min) / (n - 1));

        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] used = new boolean[bucketCount];

        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {

            int index = (num - min) / bucketSize;

            bucketMin[index] = Math.min(bucketMin[index], num);
            bucketMax[index] = Math.max(bucketMax[index], num);

            used[index] = true;
        }

        int previous = min;
        int answer = 0;

        for (int i = 0; i < bucketCount; i++) {

            if (!used[i]) {
                continue;
            }

            answer = Math.max(answer, bucketMin[i] - previous);

            previous = bucketMax[i];
        }

        return answer;
    }
}