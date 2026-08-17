class Solution {

    public int ladderLength(String beginWord,
                            String endWord,
                            List<String> wordList) {

        Set<String> words = new HashSet<>(wordList);

        if (!words.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                if (current.equals(endWord)) {
                    return level;
                }

                char[] chars = current.toCharArray();

                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        chars[j] = ch;

                        String next = new String(chars);

                        if (words.contains(next)) {

                            queue.offer(next);

                            words.remove(next);
                        }
                    }

                    chars[j] = original;
                }
            }

            level++;
        }

        return 0;
    }
}