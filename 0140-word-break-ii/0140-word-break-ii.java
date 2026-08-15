import java.util.*;

class Solution {

    private Set<String> dictionary;

    private Map<Integer, List<String>> memo;

    private String s;

    private List<String> dfs(int start) {

        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> result = new ArrayList<>();

        if (start == s.length()) {
            result.add("");
            return result;
        }

        for (int end = start + 1; end <= s.length(); end++) {

            String word = s.substring(start, end);

            if (dictionary.contains(word)) {

                List<String> suffixes = dfs(end);

                for (String suffix : suffixes) {

                    if (suffix.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + suffix);
                    }
                }
            }
        }

        memo.put(start, result);

        return result;
    }

    public List<String> wordBreak(String s,
                                  List<String> wordDict) {

        this.s = s;

        dictionary = new HashSet<>(wordDict);

        memo = new HashMap<>();

        return dfs(0);
    }
}