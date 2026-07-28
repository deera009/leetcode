class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {

            int j = i;
            int letters = 0;

            // Greedily fit as many words as possible
            while (j < words.length &&
                    letters + words[j].length() + (j - i) <= maxWidth) {
                letters += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();

            // Last line or single word
            if (j == words.length || gaps == 0) {

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k != j - 1) {
                        line.append(" ");
                    }
                }

                while (line.length() < maxWidth) {
                    line.append(" ");
                }

            } else {

                int totalSpaces = maxWidth - letters;
                int even = totalSpaces / gaps;
                int extra = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k != j - 1) {

                        for (int s = 0; s < even; s++)
                            line.append(" ");

                        if (extra > 0) {
                            line.append(" ");
                            extra--;
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}