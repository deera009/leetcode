class Solution {
    public long totalCost(int[] costs, int k, int candidates) {

        int n = costs.length;

        PriorityQueue<Integer> leftHeap = new PriorityQueue<>();
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>();

        int left = 0;
        int right = n - 1;

        // Fill left heap
        while (left < candidates && left <= right) {
            leftHeap.offer(costs[left++]);
        }

        // Fill right heap
        while (right >= n - candidates && left <= right) {
            rightHeap.offer(costs[right--]);
        }

        long ans = 0;

        while (k-- > 0) {

            if (rightHeap.isEmpty() ||
                    (!leftHeap.isEmpty() && leftHeap.peek() <= rightHeap.peek())) {

                ans += leftHeap.poll();

                if (left <= right) {
                    leftHeap.offer(costs[left++]);
                }

            } else {

                ans += rightHeap.poll();

                if (left <= right) {
                    rightHeap.offer(costs[right--]);
                }
            }
        }

        return ans;
    }
}