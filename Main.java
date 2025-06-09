import graph.GraphLink;
import graph.Edge;
import graph.Vertex;
import ListLinked.ListLinked;

public class Main {
    public static void main(String[] args) {
        // Crear un grafo
        GraphLink<String> graph = new GraphLink<>();

        // Insertar vértices (nodos)
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        // Insertar aristas (conexiones entre vértices)
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");

        // Mostrar el grafo
        System.out.println("Grafo completo:");
        System.out.println(graph);

        // Realizar un recorrido DFS desde el vértice "A"
        System.out.println("\nRecorrido DFS desde 'A':");
        graph.dfs("A");

        // Buscar una arista específica
        Edge<String> edge = graph.searchEdge("A", "B");
        if (edge != null) {
            System.out.println("\nArista encontrada: " + edge);
        } else {
            System.out.println("\nArista no encontrada.");
        }

        // Eliminar un vértice y sus conexiones
        System.out.println("\nEliminando vértice 'B' y sus conexiones...");
        graph.removeVertex("B");

        // Mostrar el grafo después de la eliminación
        System.out.println("\nGrafo después de eliminar 'B':");
        System.out.println(graph);

        // Eliminar una arista específica
        System.out.println("\nEliminando arista de 'A' a 'C'...");
        graph.removeEdge("A", "C");

        // Mostrar el grafo después de eliminar la arista
        System.out.println("\nGrafo después de eliminar la arista de 'A' a 'C':");
        System.out.println(graph);
    }
}
