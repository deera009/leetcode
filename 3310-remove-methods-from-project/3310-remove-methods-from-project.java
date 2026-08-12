import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        // Build graph
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            graph[from].add(to);
        }

        // Find all suspicious methods
        boolean[] suspicious = new boolean[n];

        dfs(k, graph, suspicious);

        // Check whether any non-suspicious method
        // invokes a suspicious method
        for (int[] edge : invocations) {

            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {
                // Cannot remove suspicious methods
                List<Integer> result = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    result.add(i);
                }

                return result;
            }
        }

        // Remove all suspicious methods
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }

    private void dfs(
        int node,
        List<Integer>[] graph,
        boolean[] suspicious
    ) {

        if (suspicious[node]) {
            return;
        }

        suspicious[node] = true;

        for (int next : graph[node]) {
            dfs(next, graph, suspicious);
        }
    }
}