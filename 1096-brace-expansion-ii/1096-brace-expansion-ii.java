import java.util.*;

class Solution {

    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    // Handles union:
    // e1,e2,e3
    private Set<String> parseExpression() {
        Set<String> result = parseConcatenation();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip ','

            Set<String> next = parseConcatenation();
            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation:
    // e1e2e3
    private Set<String> parseConcatenation() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> next = parseFactor();

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    // Handles:
    // a
    // { ... }
    private Set<String> parseFactor() {
        Set<String> result = new HashSet<>();

        char ch = s.charAt(index);

        if (ch == '{') {
            index++; // skip '{'

            result = parseExpression();

            index++; // skip '}'
        } else {
            result.add(String.valueOf(ch));
            index++;
        }

        return result;
    }
}