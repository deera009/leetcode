class Solution {

    public int singleNumber(int[] nums) {

        int answer = 0;

        for (int bit = 0; bit < 32; bit++) {

            int count = 0;

            for (int num : nums) {

                if (((num >> bit) & 1) == 1) {
                    count++;
                }
            }

            if (count % 3 != 0) {
                answer |= (1 << bit);
            }
        }

        return answer;
    }
}