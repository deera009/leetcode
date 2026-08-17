class Solution {

    public int maxProduct(int[] nums) {

        int maxProduct = 1;
        int minProduct = 1;
        int answer = nums[0];

        for (int num : nums) {

            if (num < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(num, num * maxProduct);
            minProduct = Math.min(num, num * minProduct);

            answer = Math.max(answer, maxProduct);
        }

        return answer;
    }
}