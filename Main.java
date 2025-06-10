import graph.GraphLink;
import graph.Edge;
import graph.Vertex;
import graph.GraphListEdge;
import graph.VertexObj;
import graph.EdgeObj;
import ListLinked.ListLinked;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear un grafo con lista de adyacencia (GraphLink)
        GraphLink<String> graphLink = new GraphLink<>();

        // Insertar vértices (nodos)
        graphLink.insertVertex("A");
        graphLink.insertVertex("B");
        graphLink.insertVertex("C");
        graphLink.insertVertex("D");

        // Insertar aristas (conexiones entre vértices)
        graphLink.insertEdge("A", "B");
        graphLink.insertEdge("A", "C");
        graphLink.insertEdge("B", "D");
        graphLink.insertEdge("C", "D");

        // Insertar aristas ponderadas (con peso)
        graphLink.insertEdgeWeight("A", "B", 5);
        graphLink.insertEdgeWeight("A", "C", 3);
        graphLink.insertEdgeWeight("B", "D", 2);
        graphLink.insertEdgeWeight("C", "D", 1);

        // Mostrar el grafo
        System.out.println("Grafo con lista de adyacencia completo:");
        System.out.println(graphLink);

        // Realizar un recorrido DFS desde el vértice "A"
        System.out.println("\nRecorrido DFS desde 'A' en GraphLink:");
        graphLink.dfs("A");

        // Realizar un recorrido BFS desde el vértice "A"
        System.out.println("\nRecorrido BFS desde 'A' en GraphLink:");
        graphLink.bfs("A");

        // Buscar una arista específica
        Edge<String> edge = graphLink.searchEdge("A", "B");
        if (edge != null) {
            System.out.println("\nArista encontrada en GraphLink: " + edge);
        } else {
            System.out.println("\nArista no encontrada en GraphLink.");
        }

        // Buscar el camino más corto entre "A" y "D" usando Dijkstra
        System.out.println("\nCamino más corto de 'A' a 'D' (Dijkstra) en GraphLink:");
        ArrayList<String> path = graphLink.shortPath("A", "D");  // Se obtiene el camino de la función shortPath
        if (path.isEmpty()) {
            System.out.println("No se encontró un camino en GraphLink.");
        } else {
            System.out.println("Camino encontrado en GraphLink: " + String.join(" -> ", path)); // Imprimir el camino
        }

        // Verificar si el grafo es conexo
        System.out.println("\n¿Es el grafo conexo en GraphLink?");
        System.out.println(graphLink.isConexo());

        // Eliminar un vértice y sus conexiones
        System.out.println("\nEliminando vértice 'B' y sus conexiones en GraphLink...");
        graphLink.removeVertex("B");

        // Mostrar el grafo después de la eliminación
        System.out.println("\nGrafo después de eliminar 'B' en GraphLink:");
        System.out.println(graphLink);

        // Eliminar una arista específica
        System.out.println("\nEliminando arista de 'A' a 'C' en GraphLink...");
        graphLink.removeEdge("A", "C");

        // Mostrar el grafo después de eliminar la arista
        System.out.println("\nGrafo después de eliminar la arista de 'A' a 'C' en GraphLink:");
        System.out.println(graphLink);

        // Verificar si el grafo es conexo después de la eliminación
        System.out.println("\n¿Es el grafo conexo después de las modificaciones en GraphLink?");
        System.out.println(graphLink.isConexo());


        // -------------------------------------------------------------------------------------------------------------------
        // Ahora agregamos el grafo basado en lista de aristas (GraphListEdge)

        // Crear un grafo con lista de aristas (GraphListEdge)
        GraphListEdge<String, Integer> graphListEdge = new GraphListEdge<>();

        // Insertar vértices
        graphListEdge.insertVertex("A", 0);
        graphListEdge.insertVertex("B", 1);
        graphListEdge.insertVertex("C", 2);
        graphListEdge.insertVertex("D", 3);

        // Insertar aristas
        graphListEdge.insertEdge("A", "B", 1, 0);
        graphListEdge.insertEdge("A", "C", 2, 1);
        graphListEdge.insertEdge("B", "D", 3, 2);
        graphListEdge.insertEdge("C", "D", 4, 3);

        // Mostrar el grafo
        System.out.println("\nGrafo con lista de aristas completo:");
        System.out.println(graphListEdge);

        // Realizar recorrido BFS desde 'A'
        System.out.println("\nRecorrido BFS desde 'A' en GraphListEdge:");
        graphListEdge.bfs("A");

        // Buscar arista entre 'A' y 'C'
        EdgeObj<String, Integer> edgeListEdge = graphListEdge.searchEdge("A", "C");
        if (edgeListEdge != null) {
            System.out.println("\nArista encontrada en GraphListEdge: " + edgeListEdge);
        } else {
            System.out.println("\nArista no encontrada en GraphListEdge.");
        }

        // Verificar si el grafo es conexo
        System.out.println("\n¿Es el grafo conexo en GraphListEdge?");
        System.out.println(graphListEdge.isConexo());
    }
}
