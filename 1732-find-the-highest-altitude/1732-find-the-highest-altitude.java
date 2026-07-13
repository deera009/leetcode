class Solution {
    public int largestAltitude(int[] gain) {
        int[] altitude = new int[gain.length + 1];
        int max = 0;

        for (int i = 1; i <= gain.length; i++) {
            altitude[i] = altitude[i - 1] + gain[i - 1];
            max = Math.max(max, altitude[i]);
        }

        return max;
    }
}