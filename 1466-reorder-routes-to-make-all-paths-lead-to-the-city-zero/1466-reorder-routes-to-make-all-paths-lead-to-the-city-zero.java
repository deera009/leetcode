class Solution {
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(new int[]{v, 1}); // original direction
            graph[v].add(new int[]{u, 0}); // reverse direction
        }

        boolean[] visited = new boolean[n];
        return dfs(0, graph, visited);
    }

    private int dfs(int node, List<int[]>[] graph, boolean[] visited) {
        visited[node] = true;
        int changes = 0;

        for (int[] next : graph[node]) {
            int neighbor = next[0];
            int cost = next[1];

            if (!visited[neighbor]) {
                changes += cost;
                changes += dfs(neighbor, graph, visited);
            }
        }

        return changes;
    }
}