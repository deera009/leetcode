import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {

        int[] freq = new int[26];

        // Count frequency of each character
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Sort frequencies
        Arrays.sort(freq);

        int ans = 0;

        // Process from highest frequency to lowest
        for (int i = 25; i >= 0; i--) {

            int position = 25 - i;

            int pushes = position / 8 + 1;

            ans += freq[i] * pushes;
        }

        return ans;
    }
}