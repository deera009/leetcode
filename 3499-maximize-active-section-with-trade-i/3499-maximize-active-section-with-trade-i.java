class Solution {
    static class Run {
        char ch;
        int len;

        Run(char ch, int len) {
            this.ch = ch;
            this.len = len;
        }
    }

    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";

        // Run Length Encoding
        List<Run> runs = new ArrayList<>();

        int cnt = 1;
        for (int i = 1; i < t.length(); i++) {
            if (t.charAt(i) == t.charAt(i - 1)) {
                cnt++;
            } else {
                runs.add(new Run(t.charAt(i - 1), cnt));
                cnt = 1;
            }
        }
        runs.add(new Run(t.charAt(t.length() - 1), cnt));

        int ans = ones;

        // Check every removable 1-block
        for (int i = 1; i + 1 < runs.size(); i++) {
            if (runs.get(i).ch == '1'
                    && runs.get(i - 1).ch == '0'
                    && runs.get(i + 1).ch == '0') {

                int gain = runs.get(i - 1).len + runs.get(i + 1).len;
                ans = Math.max(ans, ones + gain);
            }
        }

        return Math.min(ans, n);
    }
}