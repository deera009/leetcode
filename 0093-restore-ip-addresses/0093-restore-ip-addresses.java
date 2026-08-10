import java.util.*;

class Solution {

    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();

        backtrack(s, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(
            String s,
            int index,
            int parts,
            StringBuilder current,
            List<String> result) {

        // Exactly 4 parts
        if (parts == 4) {

            // All digits must be used
            if (index == s.length()) {
                result.add(current.toString());
            }

            return;
        }

        // Try taking 1, 2, or 3 digits
        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length()) {
                break;
            }

            String part = s.substring(index, index + len);

            // Leading zero
            if (part.length() > 1 && part.charAt(0) == '0') {
                break;
            }

            // Value must be <= 255
            if (Integer.parseInt(part) > 255) {
                break;
            }

            // Add dot before every part except the first
            if (parts > 0) {
                current.append('.');
            }

            current.append(part);

            // Choose
            backtrack(
                    s,
                    index + len,
                    parts + 1,
                    current,
                    result
            );

            // Undo
            current.setLength(
                    current.length() - part.length() - (parts > 0 ? 1 : 0)
            );
        }
    }
}