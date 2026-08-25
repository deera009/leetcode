import java.util.HashSet;
import java.util.Set;

class Solution {

    public boolean isRectangleCover(int[][] rectangles) {

        Set<String> corners = new HashSet<>();

        long area = 0;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int[] rect : rectangles) {

            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];

            // Calculate total area
            area += (long) (x2 - x1) * (y2 - y1);

            // Find bounding rectangle
            minX = Math.min(minX, x1);
            minY = Math.min(minY, y1);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);

            // Toggle all four corners
            toggle(corners, x1, y1);
            toggle(corners, x1, y2);
            toggle(corners, x2, y1);
            toggle(corners, x2, y2);
        }

        // Area of the bounding rectangle
        long boundingArea =
                (long) (maxX - minX) * (maxY - minY);

        // Area must match
        if (area != boundingArea) {
            return false;
        }

        // Exactly four corners must remain
        if (corners.size() != 4) {
            return false;
        }

        // The remaining four corners must be
        // the four corners of the bounding rectangle
        return corners.contains(key(minX, minY))
                && corners.contains(key(minX, maxY))
                && corners.contains(key(maxX, minY))
                && corners.contains(key(maxX, maxY));
    }

    private void toggle(Set<String> set, int x, int y) {

        String point = key(x, y);

        if (!set.add(point)) {
            set.remove(point);
        }
    }

    private String key(int x, int y) {
        return x + "," + y;
    }
}