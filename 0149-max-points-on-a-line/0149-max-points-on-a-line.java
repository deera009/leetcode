class Solution {

    public int maxPoints(int[][] points) {

        int n = points.length;

        if (n <= 2) {
            return n;
        }

        int answer = 2;

        for (int i = 0; i < n; i++) {

            HashMap<String, Integer> map = new HashMap<>();

            int currentMax = 0;

            for (int j = i + 1; j < n; j++) {

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                int g = gcd(dx, dy);

                dx /= g;
                dy /= g;

                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }

                if (dx == 0) {
                    dy = 1;
                }

                if (dy == 0) {
                    dx = 1;
                }

                String slope = dy + ":" + dx;

                map.put(slope, map.getOrDefault(slope, 0) + 1);

                currentMax = Math.max(currentMax, map.get(slope));
            }

            answer = Math.max(answer, currentMax + 1);
        }

        return answer;
    }

    private int gcd(int a, int b) {

        if (b == 0) {
            return Math.abs(a);
        }

        return gcd(b, a % b);
    }
}