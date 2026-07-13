import java.util.*;

class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> map = new HashMap<>();

        // Store all rows
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]).append(",");
            }

            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int count = 0;

        // Check every column
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n; i++) {
                sb.append(grid[i][j]).append(",");
            }

            count += map.getOrDefault(sb.toString(), 0);
        }

        return count;
    }
}