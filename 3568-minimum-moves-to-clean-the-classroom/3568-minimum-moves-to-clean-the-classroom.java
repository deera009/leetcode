import java.util.*;

class Solution {

    static class State {
        int r, c, energy, mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();   // FIXED

        int startR = 0;
        int startC = 0;

        // Assign a bit to every litter cell.
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        // Find starting position and litter positions.
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                }

                if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        // No litter to collect.
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;
        int targetMask = totalMasks - 1;

        /*
         * maxEnergy[r][c][mask]
         *
         * Stores the maximum remaining energy with which
         * we have reached (r, c) after collecting 'mask'.
         */
        int[][][] maxEnergy = new int[m][n][totalMasks];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(maxEnergy[r][c], -1);
            }
        }

        Queue<State> queue = new ArrayDeque<>();

        // Initial state.
        queue.offer(new State(startR, startC, energy, 0));
        maxEnergy[startR][startC][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process all states having the same distance.
            while (size-- > 0) {

                State curr = queue.poll();

                int r = curr.r;
                int c = curr.c;
                int currEnergy = curr.energy;
                int mask = curr.mask;

                // All litter collected.
                if (mask == targetMask) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Boundary check.
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Cannot walk through obstacles.
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // One move consumes one energy.
                    int newEnergy = currEnergy - 1;

                    if (newEnergy < 0) {
                        continue;
                    }

                    int newMask = mask;

                    // Collect litter.
                    if (classroom[nr].charAt(nc) == 'L') {

                        int id = litterId[nr][nc];

                        newMask |= (1 << id);
                    }

                    // Reset energy at R.
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    /*
                     * If we have already reached this exact
                     * (position + mask) with MORE energy,
                     * this state cannot provide a better solution.
                     */
                    if (maxEnergy[nr][nc][newMask] >= newEnergy) {
                        continue;
                    }

                    maxEnergy[nr][nc][newMask] = newEnergy;

                    queue.offer(
                        new State(nr, nc, newEnergy, newMask)
                    );
                }
            }

            moves++;
        }

        return -1;
    }
}