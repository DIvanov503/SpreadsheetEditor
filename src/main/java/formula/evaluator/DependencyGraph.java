package formula.evaluator;

import formula.AST.CellReference;

import java.util.*;

public class DependencyGraph {
    Map<String, Set<String>> adjacencyMatrix = new HashMap<>();

    Map<String, Color> colorMap = new HashMap<>();
    Set<String> vertexSet = new HashSet<>();

    public void addDependency(String from, String to) {
        System.out.println(from+"->"+to);
        if (adjacencyMatrix.keySet().contains(from)) {
            adjacencyMatrix.get(from).add(to);
        } else {
            Set<String> dests = new HashSet<String>();
            dests.add(to);
            adjacencyMatrix.put(from, dests);
        }
        vertexSet.add(from);
        vertexSet.add(to);
    }

    public void topologicalSort() throws CyclicDependencyException {
        System.out.println(vertexSet.size());
        vertexSet.forEach(v -> colorMap.put(v, Color.White));
        for (String vertex : vertexSet) {
            if (colorMap.get(vertex) == Color.White) {
                DFSVisit(vertex);
            }
        }
    }

    private void DFSVisit(String vertex) throws CyclicDependencyException {
        System.out.println(colorMap.get(vertex));
        if (colorMap.get(vertex) == Color.Gray) {
            throw new CyclicDependencyException();
        }
        colorMap.put(vertex, Color.Gray);
        for (String successor : adjacencyMatrix.getOrDefault(vertex, new HashSet<>(0))) {
            DFSVisit(successor);
        }
        colorMap.put(vertex, Color.Black);
    }

    private enum Color {
        White,
        Gray,
        Black
    }
}
