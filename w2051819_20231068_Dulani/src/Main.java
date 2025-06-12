import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filename = "/Users/dulanikamkanamge/Documents/CW Algo/w2051819_20231068_Dulani/benchmarks/bridge_5.txt"; // Ensure this file is in the root directory

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int n = Integer.parseInt(br.readLine().trim());
            FlowGraph graph = new FlowGraph(n);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 3) {
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    int cap = Integer.parseInt(parts[2]);
                    graph.addEdge(from, to, cap);
                }
            }

            EdmondsKarp ek = new EdmondsKarp(graph, 0, n - 1);
            int maxFlow = ek.maxFlow();
            System.out.println("Maximum Flow: " + maxFlow);

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}