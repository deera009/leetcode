import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();

        backtrack(
            num,
            target,
            0,
            0,
            0,
            new StringBuilder(),
            result
        );

        return result;
    }

    private void backtrack(
        String num,
        long target,
        int index,
        long currentValue,
        long previousOperand,
        StringBuilder expression,
        List<String> result
    ) {

        // All digits have been used
        if (index == num.length()) {

            if (currentValue == target) {
                result.add(expression.toString());
            }

            return;
        }

        int expressionLength = expression.length();

        // Try every possible next number
        for (int i = index; i < num.length(); i++) {

            // Leading zero is not allowed
            if (i > index && num.charAt(index) == '0') {
                break;
            }

            String currentString = num.substring(index, i + 1);

            long currentNumber = Long.parseLong(currentString);

            // First number: no operator before it
            if (index == 0) {

                expression.append(currentString);

                backtrack(
                    num,
                    target,
                    i + 1,
                    currentNumber,
                    currentNumber,
                    expression,
                    result
                );

                expression.setLength(expressionLength);

            } else {

                // +
                expression.append("+").append(currentString);

                backtrack(
                    num,
                    target,
                    i + 1,
                    currentValue + currentNumber,
                    currentNumber,
                    expression,
                    result
                );

                expression.setLength(expressionLength);

                // -
                expression.append("-").append(currentString);

                backtrack(
                    num,
                    target,
                    i + 1,
                    currentValue - currentNumber,
                    -currentNumber,
                    expression,
                    result
                );

                expression.setLength(expressionLength);

                // *
                expression.append("*").append(currentString);

                backtrack(
                    num,
                    target,
                    i + 1,
                    currentValue
                        - previousOperand
                        + previousOperand * currentNumber,
                    previousOperand * currentNumber,
                    expression,
                    result
                );

                expression.setLength(expressionLength);
            }
        }
    }
}