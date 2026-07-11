class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        Arrays.sort(potions);

        int m = potions.length;
        int[] ans = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {

            int left = 0;
            int right = m - 1;
            int idx = m;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if ((long) spells[i] * potions[mid] >= success) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            ans[i] = m - idx;
        }

        return ans;
    }
}