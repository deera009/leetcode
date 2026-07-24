class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] dp1 = new boolean[MAX];
        boolean[] dp2 = new boolean[MAX];
        boolean[] dp3 = new boolean[MAX];

        // XOR using exactly one element
        for (int x : nums) {
            dp1[x] = true;
        }

        // XOR using exactly two elements
        for (int x = 0; x < MAX; x++) {
            if (!dp1[x]) continue;
            for (int num : nums) {
                dp2[x ^ num] = true;
            }
        }

        // XOR using exactly three elements
        for (int x = 0; x < MAX; x++) {
            if (!dp2[x]) continue;
            for (int num : nums) {
                dp3[x ^ num] = true;
            }
        }

        int ans = 0;
        for (boolean b : dp3) {
            if (b) ans++;
        }

        return ans;
    }
}