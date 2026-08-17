class Solution {

    private Map<String, List<String>> parents = new HashMap<>();

    private List<List<String>> result = new ArrayList<>();

    private void buildPaths(String word,
                            String beginWord,
                            List<String> path) {

        if (word.equals(beginWord)) {

            List<String> sequence = new ArrayList<>(path);

            Collections.reverse(sequence);

            result.add(sequence);

            return;
        }

        if (!parents.containsKey(word)) {
            return;
        }

        for (String parent : parents.get(word)) {

            path.add(parent);

            buildPaths(parent, beginWord, path);

            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> findLadders(String beginWord,
                                          String endWord,
                                          List<String> wordList) {

        Set<String> dictionary = new HashSet<>(wordList);

        if (!dictionary.contains(endWord)) {
            return result;
        }

        Queue<String> queue = new LinkedList<>();

        Map<String, Integer> distance = new HashMap<>();

        queue.offer(beginWord);

        distance.put(beginWord, 0);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            char[] chars = current.toCharArray();

            for (int i = 0; i < chars.length; i++) {

                char original = chars[i];

                for (char ch = 'a'; ch <= 'z'; ch++) {

                    if (ch == original) {
                        continue;
                    }

                    chars[i] = ch;

                    String next = new String(chars);

                    if (!dictionary.contains(next)) {
                        continue;
                    }

                    if (!distance.containsKey(next)) {

                        distance.put(next,
                                     distance.get(current) + 1);

                        queue.offer(next);

                        parents.putIfAbsent(next,
                                            new ArrayList<>());

                        parents.get(next).add(current);

                    } else if (distance.get(next)
                            == distance.get(current) + 1) {

                        parents.get(next).add(current);
                    }
                }

                chars[i] = original;
            }
        }

        if (!distance.containsKey(endWord)) {
            return result;
        }

        List<String> path = new ArrayList<>();

        path.add(endWord);

        buildPaths(endWord, beginWord, path);

        return result;
    }
}