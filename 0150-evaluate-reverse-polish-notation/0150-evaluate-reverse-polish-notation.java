class Solution {

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/")) {

                int second = stack.pop();
                int first = stack.pop();

                switch (token) {

                    case "+":
                        stack.push(first + second);
                        break;

                    case "-":
                        stack.push(first - second);
                        break;

                    case "*":
                        stack.push(first * second);
                        break;

                    case "/":
                        stack.push(first / second);
                        break;
                }

            } else {

                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}