import java.util.*;
public class ValidTree {
    static class Graph {
        private final int n;
        private final List<List<Integer>> adjList;
        public Graph(int n) {
            this.n = n;
            adjList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
        }
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        public boolean isTree() {
            boolean[] visited = new boolean[n];
            if (hasCycle(-1, 0, visited)) {
                return false;
            }
            for (boolean nodeVisited : visited) {
                if (!nodeVisited) {
                    return false;
                }
            }
            int edgeCount = 0;
            for (List<Integer> neighbors : adjList) {
                edgeCount += neighbors.size();
            }
            return edgeCount / 2 == n - 1;
        }
        private boolean hasCycle(int parent, int node, boolean[] visited) {
            visited[node] = true;
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    if (hasCycle(node, neighbor, visited)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    return true;
                }
            }
            return false;
        }
    }
    public static void main(String[] args) {
        int n = 5;
        Graph graph = new Graph(n);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        boolean isValidTree = graph.isTree();
        System.out.println("Is the graph a valid tree ? : " + isValidTree);
    }
}
