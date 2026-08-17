class Solution {

    public String smallestPalindrome(String s, int k) {

        int n = s.length();
        int m = n / 2;

        int[] total = new int[26];

        for (int i = 0; i < m; i++) {
            total[s.charAt(i) - 'a']++;
        }

        int[] cnt = new int[26];

        long perm = 1;

        int i = m - 1;
        int j = 25;

        while (i >= 0 && perm < k) {

            while (j >= 0 && cnt[j] == total[j]) {
                j--;
            }

            if (j < 0) {
                break;
            }

            cnt[j]++;

            perm = perm * (m - i) / cnt[j];

            i--;
        }

        if (perm < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        for (int ch = 0; ch <= j; ch++) {

            int repeat = total[ch] - cnt[ch];

            while (repeat-- > 0) {
                left.append((char) ('a' + ch));
            }
        }

        int start = j;

        i++;

        while (i < m) {

            for (int ch = start; ch < 26; ch++) {

                if (cnt[ch] == 0) {
                    continue;
                }

                long p = perm * cnt[ch] / (m - i);

                if (p >= k) {

                    left.append((char) ('a' + ch));

                    perm = p;

                    cnt[ch]--;

                    break;

                } else {

                    k -= p;
                }
            }

            i++;
        }

        StringBuilder result = new StringBuilder();

        result.append(left);

        if ((n & 1) == 1) {
            result.append(s.charAt(n / 2));
        }

        result.append(new StringBuilder(left).reverse());

        return result.toString();
    }
}