import java.util.*;

class Solution {

    private Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        // Find the minimum number of parentheses to remove
        int leftRemove = 0;
        int rightRemove = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;
            } 
            else if (c == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                } 
                else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, leftRemove, rightRemove);

        return new ArrayList<>(result);
    }

    private void dfs(String s, int start,
                     int leftRemove, int rightRemove) {

        // If all required removals are done,
        // check whether the string is valid.
        if (leftRemove == 0 && rightRemove == 0) {

            if (isValid(s)) {
                result.add(s);
            }

            return;
        }

        for (int i = start; i < s.length(); i++) {

            // Only parentheses can be removed
            if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                continue;
            }

            // Avoid generating duplicate strings
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            // Remove ')'
            if (rightRemove > 0 && s.charAt(i) == ')') {

                String next = s.substring(0, i)
                           + s.substring(i + 1);

                dfs(next, i, leftRemove, rightRemove - 1);
            }

            // Remove '('
            if (leftRemove > 0 && s.charAt(i) == '(') {

                String next = s.substring(0, i)
                           + s.substring(i + 1);

                dfs(next, i, leftRemove - 1, rightRemove);
            }
        }
    }

    // Checks whether a string has valid parentheses
    private boolean isValid(String s) {

        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            } 
            else if (c == ')') {

                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}