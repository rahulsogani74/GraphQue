import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
class NetworkDelayTime {
    class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    class Node implements Comparable<Node> {
        int id;
        int distance;
        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    class Graph {
        private Map<Integer, List<Edge>> adjacencyList;
        Graph(int n) {
            adjacencyList = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }
        public void addEdge(int from, int to, int weight) {
            List<Edge> edges = adjacencyList.get(from);
            edges.add(new Edge(to, weight));
        }
        public List<Edge> getAdjacencyList(int node) {
            return adjacencyList.get(node);
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        Graph graph = new Graph(n);
        for (int[] time : times) {
            graph.addEdge(time[0], time[1], time[2]);
        }
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;
        dijkstra(graph, distance, k);
        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            maxDistance = Math.max(maxDistance, distance[i]);
        }
        return (maxDistance == Integer.MAX_VALUE) ? -1 : maxDistance;
    }
    private void dijkstra(Graph graph, int[] distance, int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Edge edge : graph.getAdjacencyList(node.id)) {
                int newDistance = node.distance + edge.weight;
                if (newDistance < distance[edge.to]) {
                    distance[edge.to] = newDistance;
                    queue.add(new Node(edge.to, newDistance));
                }
            }
        }
    }
    public static void main(String[] args) {
        NetworkDelayTime solution = new NetworkDelayTime();
        int[][] times = new int[][] { { 2, 1, 1 }, { 2, 3, 2 }, { 3, 4, 1 }, { 4, 1, 1 } };
        int n = 4;
        int k = 2;
        int maxDistance = solution.networkDelayTime(times, n, k);
        System.out.println("Max Network Delay Time: " + maxDistance);
    }
}


