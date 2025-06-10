package graph;

import java.util.*;

public class GraphListEdge<V, E> {
    private ArrayList<VertexObj<V, E>> secVertex; // Lista de vértices
    private ArrayList<EdgeObj<V, E>> secEdge;    // Lista de aristas

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // Método para insertar un vértice
    public void insertVertex(V v, int position) {
        if (searchVertex(v) == null) {
            secVertex.add(new VertexObj<>(v, position));
        }
    }

    // Método para insertar una arista entre dos vértices
    public void insertEdge(V v1, V v2, E info, int position) {
        VertexObj<V, E> vertex1 = searchVertex(v1);
        VertexObj<V, E> vertex2 = searchVertex(v2);
        if (vertex1 != null && vertex2 != null) {
            EdgeObj<V, E> edge = new EdgeObj<>(vertex1, vertex2, info, position);
            if (searchEdge(v1, v2) == null) {
                secEdge.add(edge);
            }
        }
    }

    // Método para buscar un vértice
    public VertexObj<V, E> searchVertex(V v) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v)) {
                return vertex;
            }
        }
        return null;
    }

    // Método para buscar una arista entre dos vértices
    public EdgeObj<V, E> searchEdge(V v1, V v2) {
        for (EdgeObj<V, E> edge : secEdge) {
            if ((edge.getEndVertex1().getInfo().equals(v1) && edge.getEndVertex2().getInfo().equals(v2)) ||
                    (edge.getEndVertex1().getInfo().equals(v2) && edge.getEndVertex2().getInfo().equals(v1))) {
                return edge;
            }
        }
        return null;
    }

    // Método para realizar un recorrido BFS desde un vértice
    public void bfs(V startData) {
        VertexObj<V, E> startVertex = searchVertex(startData);
        if (startVertex == null) return;

        Set<VertexObj<V, E>> visited = new HashSet<>();
        Queue<VertexObj<V, E>> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.println(current.getInfo());
            for (EdgeObj<V, E> edge : secEdge) {
                if (edge.getEndVertex1().equals(current) && !visited.contains(edge.getEndVertex2())) {
                    visited.add(edge.getEndVertex2());
                    queue.add(edge.getEndVertex2());
                } else if (edge.getEndVertex2().equals(current) && !visited.contains(edge.getEndVertex1())) {
                    visited.add(edge.getEndVertex1());
                    queue.add(edge.getEndVertex1());
                }
            }
        }
    }

    // Método para verificar si el grafo es conexo
    public boolean isConexo() {
        if (secVertex.isEmpty()) {
            return true; // Si no hay vértices, el grafo se considera conexo
        }

        Set<VertexObj<V, E>> visited = new HashSet<>();
        Queue<VertexObj<V, E>> queue = new LinkedList<>();

        // Comienza el recorrido desde el primer vértice
        VertexObj<V, E> startVertex = secVertex.get(0);
        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();

            // Agregar a la cola los vértices adyacentes no visitados
            for (EdgeObj<V, E> edge : secEdge) {
                if (edge.getEndVertex1().equals(current) && !visited.contains(edge.getEndVertex2())) {
                    visited.add(edge.getEndVertex2());
                    queue.add(edge.getEndVertex2());
                } else if (edge.getEndVertex2().equals(current) && !visited.contains(edge.getEndVertex1())) {
                    visited.add(edge.getEndVertex1());
                    queue.add(edge.getEndVertex1());
                }
            }
        }

        // Si todos los vértices han sido visitados, el grafo es conexo
        return visited.size() == secVertex.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (VertexObj<V, E> vertex : secVertex) {
            sb.append(vertex.getInfo()).append(": ");
            for (EdgeObj<V, E> edge : secEdge) {
                if (edge.getEndVertex1().equals(vertex)) {
                    sb.append(edge.getEndVertex2().getInfo()).append(" ");
                } else if (edge.getEndVertex2().equals(vertex)) {
                    sb.append(edge.getEndVertex1().getInfo()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
