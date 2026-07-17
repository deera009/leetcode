class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int MAX = -1000000;
        int MIN = 1000000;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] < MIN) MIN = arr[i];
            if (arr[i] > MAX) MAX = arr[i];
        }

        if ((MAX-MIN)%(arr.length-1) != 0) return false;
        int delta = (MAX-MIN)/(arr.length-1);

        if(delta == 0) return true;

        boolean[] exist = new boolean[arr.length];
        for (int i=0; i<arr.length; i++) {
            if ((arr[i] - MIN)%delta != 0) return false;
            int n = (arr[i]-MIN)/delta;           // arr[i] = MIN + delta * n 

            if (exist[n]) return false;
            else exist[n] = true;
        }
        return true;
    }
}