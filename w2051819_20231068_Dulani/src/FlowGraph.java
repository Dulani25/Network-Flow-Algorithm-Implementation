import java.util.*;

public class FlowGraph {
    private final ArrayList<Edge>[] graph;

    @SuppressWarnings("unchecked")
    public FlowGraph(int nodeCount) {
        graph = new ArrayList[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(to, graph[to].size(), capacity);
        Edge reverse = new Edge(from, graph[from].size(), 0);
        graph[from].add(forward);
        graph[to].add(reverse);
    }

    public ArrayList<Edge>[] getGraph() {
        return graph;
    }
}