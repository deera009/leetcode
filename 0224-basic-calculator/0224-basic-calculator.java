import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int number = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Build the number
            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            }

            // Addition
            else if (ch == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            }

            // Subtraction
            else if (ch == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            }

            // Opening parenthesis
            else if (ch == '(') {
                stack.push(result);
                stack.push(sign);

                result = 0;
                sign = 1;
            }

            // Closing parenthesis
            else if (ch == ')') {
                result += sign * number;
                number = 0;

                int previousSign = stack.pop();
                int previousResult = stack.pop();

                result = previousResult + previousSign * result;
            }
        }

        // Add the final number
        result += sign * number;

        return result;
    }
}