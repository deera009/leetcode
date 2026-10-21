
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        if (isSorted(s)) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char min = 'z';
        char max = 'a';
        int minCount = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c < min) {
                min = c;
                minCount = 1;
            } else if (c == min) {
                minCount++;
            }

            if (c > max) {
                max = c;
                maxCount = 1;
            } else if (c == max) {
                maxCount++;
            }
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        if (first == min || last == max) {
            return 1;
        }

        if (first == max && last == min
                && maxCount == 1 && minCount == 1) {
            return 3;
        }

        return 2;
    }

    private boolean isSorted(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                return false;
            }
        }

        return true;
    }
}
