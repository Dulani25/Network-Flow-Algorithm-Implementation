import java.util.*;

public class EdmondsKarp {
    private final ArrayList<Edge>[] graph;
    private final int nodeCount;
    private final int source;
    private final int sink;

    public EdmondsKarp(FlowGraph flowGraph, int source, int sink) {
        this.graph = flowGraph.getGraph();
        this.nodeCount = graph.length;
        this.source = source;
        this.sink = sink;
    }

    private boolean bfs(int[] parentNode, Edge[] parentEdge) {
        Arrays.fill(parentNode, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        parentNode[source] = source;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : graph[u]) {
                if (parentNode[e.to] == -1 && e.capacity > e.flow) {
                    parentNode[e.to] = u;
                    parentEdge[e.to] = e;
                    if (e.to == sink) return true;
                    q.add(e.to);
                }
            }
        }
        return false;
    }

    public int maxFlow() {
        int flow = 0;
        int[] parentNode = new int[nodeCount];
        Edge[] parentEdge = new Edge[nodeCount];

        while (bfs(parentNode, parentEdge)) {
            int bottleneck = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parentNode[v]) {
                bottleneck = Math.min(bottleneck, parentEdge[v].capacity - parentEdge[v].flow);
            }
            for (int v = sink; v != source; v = parentNode[v]) {
                parentEdge[v].flow += bottleneck;
                graph[parentEdge[v].to].get(parentEdge[v].rev).flow -= bottleneck;
            }
            flow += bottleneck;
            System.out.println("Augmented path with bottleneck: " + bottleneck + ", total flow: " + flow);
        }

        return flow;
    }
}