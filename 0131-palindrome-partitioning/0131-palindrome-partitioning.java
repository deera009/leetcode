import java.util.*;

class Solution {

    private List<List<String>> result = new ArrayList<>();

    private boolean isPalindrome(String s, int left, int right) {

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    private void backtrack(String s, int start, List<String> current) {

        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < s.length(); end++) {

            if (isPalindrome(s, start, end)) {

                current.add(s.substring(start, end + 1));

                backtrack(s, end + 1, current);

                current.remove(current.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {

        backtrack(s, 0, new ArrayList<>());

        return result;
    }
}