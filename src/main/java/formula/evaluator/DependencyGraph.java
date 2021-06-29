package formula.evaluator;

import formula.AST.CellReference;
import spreadsheet.ICell;

import java.util.*;

public class DependencyGraph {
    Map<ICell, Set<ICell>> adjacencyMatrix = new HashMap<>();

    Map<ICell, Color> colorMap = new HashMap<>();
    Set<ICell> vertexSet = new HashSet<>();

    List<ICell> sortedCells = new ArrayList<>();

    public void addDependency(ICell from, ICell to) {
        System.out.println(from+"->"+to);
        if (adjacencyMatrix.keySet().contains(from)) {
            adjacencyMatrix.get(from).add(to);
        } else {
            Set<ICell> dests = new HashSet<>();
            dests.add(to);
            adjacencyMatrix.put(from, dests);
        }
        vertexSet.add(from);
        vertexSet.add(to);
    }

    public void removeDependenciesFrom(ICell from) {
        adjacencyMatrix.remove(from);
        vertexSet.clear();
        vertexSet.addAll(adjacencyMatrix.keySet());
        adjacencyMatrix.values().forEach((vertices) -> vertexSet.addAll(vertices));
    }

    public List<ICell> topologicalSort() throws CyclicDependencyException {
        System.out.println(vertexSet.size());
        sortedCells.clear();
        vertexSet.forEach(v -> colorMap.put(v, Color.White));
        for (ICell vertex : vertexSet) {
            if (colorMap.get(vertex) == Color.White) {
                DFSVisit(vertex);
            }
        }
        return sortedCells;
    }

    private void DFSVisit(ICell vertex) throws CyclicDependencyException {
        System.out.println(colorMap.get(vertex));
        if (colorMap.get(vertex) == Color.Gray) {
            throw new CyclicDependencyException();
        }
        colorMap.put(vertex, Color.Gray);
        for (ICell successor : adjacencyMatrix.getOrDefault(vertex, new HashSet<>(0))) {
            DFSVisit(successor);
        }
        colorMap.put(vertex, Color.Black);
        sortedCells.add(vertex);
    }

    private enum Color {
        White,
        Gray,
        Black
    }
}
