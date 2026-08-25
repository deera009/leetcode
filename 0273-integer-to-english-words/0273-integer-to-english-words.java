class Solution {

    private final String[] belowTwenty = {
        "", "One", "Two", "Three", "Four",
        "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
        "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {

        if (num == 0) {
            return "Zero";
        }

        StringBuilder result = new StringBuilder();

        int[] values = {
            1_000_000_000,
            1_000_000,
            1_000,
            1
        };

        String[] names = {
            "Billion",
            "Million",
            "Thousand",
            ""
        };

        for (int i = 0; i < values.length; i++) {

            if (num >= values[i]) {

                int group = num / values[i];

                result.append(convertHundred(group));

                if (!names[i].isEmpty()) {
                    result.append(" ").append(names[i]);
                }

                num %= values[i];

                if (num > 0) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    private String convertHundred(int num) {

        StringBuilder result = new StringBuilder();

        if (num >= 100) {
            result.append(belowTwenty[num / 100])
                  .append(" Hundred");

            num %= 100;

            if (num > 0) {
                result.append(" ");
            }
        }

        if (num >= 20) {
            result.append(tens[num / 10]);

            num %= 10;

            if (num > 0) {
                result.append(" ");
            }
        }

        if (num > 0) {
            result.append(belowTwenty[num]);
        }

        return result.toString();
    }
}