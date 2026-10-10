
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;

        int[] freq = new int[100001];
        int maxDiff = 0;
        long sum = 0;

        for (int i = 0; i < nums1.length; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            freq[d]++;
            maxDiff = Math.max(maxDiff, d);
            sum += d;
        }

        if (sum <= k) {
            return 0;
        }

        for (int d = maxDiff; d > 0 && k > 0; d--) {
            int count = freq[d];

            if (count == 0) {
                continue;
            }

            long moves = Math.min(k, (long) count);

            freq[d] -= (int) moves;
            freq[d - 1] += (int) moves;
            k -= moves;
        }

        long ans = 0;

        for (int d = 1; d <= 100000; d++) {
            ans += (long) d * d * freq[d];
        }

        return ans;
    }
}
