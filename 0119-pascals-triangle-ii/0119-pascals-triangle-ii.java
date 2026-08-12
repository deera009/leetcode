import java.util.*;

class Solution {

    public List<Integer> getRow(int rowIndex) {

        List<Integer> row = new ArrayList<>();

        long value = 1;

        for (int k = 0; k <= rowIndex; k++) {

            row.add((int) value);

            value = value * (rowIndex - k) / (k + 1);
        }

        return row;
    }
}