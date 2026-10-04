class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            } 
            else if (ch == ')') {
                minOpen--;
                maxOpen--;
            } 
            else { // '*'
                // '*' can be ')', empty, or '('
                minOpen--;
                maxOpen++;
            }

            // minOpen cannot be negative
            if (minOpen < 0) {
                minOpen = 0;
            }

            // Even the maximum possible opening count is negative
            if (maxOpen < 0) {
                return false;
            }
        }

        return minOpen == 0;
    }
}