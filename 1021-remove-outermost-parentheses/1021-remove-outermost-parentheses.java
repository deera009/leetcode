class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                // If depth is already > 0,
                // this '(' is not an outermost parenthesis.
                if (depth > 0) {
                    result.append(c);
                }

                depth++;
            } 
            else {
                depth--;

                // If depth is still > 0,
                // this ')' is not an outermost parenthesis.
                if (depth > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}