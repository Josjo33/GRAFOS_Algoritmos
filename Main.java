import graph.GraphLink;
import graph.Edge;
import graph.Vertex;
import ListLinked.ListLinked;

import java.util.ArrayList;

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

        // Insertar aristas ponderadas (con peso)
        graph.insertEdgeWeight("A", "B", 5);
        graph.insertEdgeWeight("A", "C", 3);
        graph.insertEdgeWeight("B", "D", 2);
        graph.insertEdgeWeight("C", "D", 1);

        // Mostrar el grafo
        System.out.println("Grafo completo:");
        System.out.println(graph);

        // Realizar un recorrido DFS desde el vértice "A"
        System.out.println("\nRecorrido DFS desde 'A':");
        graph.dfs("A");

        // Realizar un recorrido BFS desde el vértice "A"
        System.out.println("\nRecorrido BFS desde 'A':");
        graph.bfs("A");

        // Buscar una arista específica
        Edge<String> edge = graph.searchEdge("A", "B");
        if (edge != null) {
            System.out.println("\nArista encontrada: " + edge);
        } else {
            System.out.println("\nArista no encontrada.");
        }

        // Buscar el camino más corto entre "A" y "D" usando Dijkstra
        System.out.println("\nCamino más corto de 'A' a 'D' (Dijkstra):");
        ArrayList<String> path = graph.shortPath("A", "D");  // Se obtiene el camino de la función shortPath
        if (path.isEmpty()) {
            System.out.println("No se encontró un camino.");
        } else {
            System.out.println("Camino encontrado: " + String.join(" -> ", path)); // Imprimir el camino
        }


        // Verificar si el grafo es conexo
        System.out.println("\n¿Es el grafo conexo?");
        System.out.println(graph.isConexo());

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

        // Verificar si el grafo es conexo después de la eliminación
        System.out.println("\n¿Es el grafo conexo después de las modificaciones?");
        System.out.println(graph.isConexo());



    }
}

