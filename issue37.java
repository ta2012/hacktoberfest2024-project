import java.util.*;

class Graph {
    private int vertices;
    private List<List<Node>> adjacencyList;

    static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Node(destination, weight));
        adjacencyList.get(destination).add(new Node(source, weight)); // For undirected graph
    }

    void dijkstra(int startVertex) {
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;
        priorityQueue.add(new Node(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            int currentVertex = priorityQueue.poll().vertex;

            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for (Node neighbor : adjacencyList.get(currentVertex)) {
                int newDist = distances[currentVertex] + neighbor.weight;

                if (newDist < distances[neighbor.vertex]) {
                    distances[neighbor.vertex] = newDist;
                    priorityQueue.add(new Node(neighbor.vertex, newDist));
                }
            }
        }

        printShortestPaths(distances);
    }

    void printShortestPaths(int[] distances) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < distances.length; i++) {
            System.out.println(i + "\t\t" + distances[i]);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 3, 9);

        graph.dijkstra(0);
    }
}
