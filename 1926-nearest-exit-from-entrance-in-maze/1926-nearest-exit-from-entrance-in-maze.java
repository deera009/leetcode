class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {

        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{entrance[0], entrance[1]});

        maze[entrance[0]][entrance[1]] = '+'; // mark visited

        int[][] dir = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                if ((r == 0 || r == m - 1 || c == 0 || c == n - 1)
                        && !(r == entrance[0] && c == entrance[1])) {
                    return steps;
                }

                for (int[] d : dir) {

                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr >= 0 && nr < m &&
                        nc >= 0 && nc < n &&
                        maze[nr][nc] == '.') {

                        maze[nr][nc] = '+';   // visited
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}