package graph;

import ListLinked.ListLinked;
import java.util.*;

//PAQUETE "graph"
public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void insertVertex(E data) {
        if (searchVertex(data) == null) {
            listVertex.add(new Vertex<>(data));
        }
    }

    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vOri = searchVertex(verOri);
        Vertex<E> vDes = searchVertex(verDes);
        if (vOri != null && vDes != null) {
            vOri.getListAdj().add(new Edge<>(vDes));
        }
    }

    public Vertex<E> searchVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data))
                return v;
        }
        return null;
    }

    public Edge<E> searchEdge(E ori, E des) {
        Vertex<E> vOri = searchVertex(ori);
        if (vOri != null) {
            for (Edge<E> edge : vOri.getListAdj()) {
                if (edge.getRefDest().getData().equals(des))
                    return edge;
            }
        }
        return null;
    }

    public void removeVertex(E data) {
        Vertex<E> v = searchVertex(data);
        if (v != null) {
            listVertex.remove(v);
            for (Vertex<E> vert : listVertex) {
                vert.getListAdj().remove(new Edge<>(v));
            }
        }
    }

    public void removeEdge(E ori, E des) {
        Vertex<E> vOri = searchVertex(ori);
        Vertex<E> vDes = searchVertex(des);
        if (vOri != null && vDes != null) {
            vOri.getListAdj().remove(new Edge<>(vDes));
        }
    }

    public void dfs(E data) {
        Vertex<E> v = searchVertex(data);
        if (v == null)
            return;
        Set<Vertex<E>> visited = new HashSet<>();
        dfsRecursive(v, visited);
    }
    public boolean isConexo() {
        if (listVertex.isEmpty()) return true;  // Si no hay vértices, el grafo se considera conexo
        HashSet<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        // Tomamos el primer vértice de la lista
        Vertex<E> start = listVertex.get(0);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> v = queue.poll();

            // Recorremos los adyacentes del vértice
            for (Edge<E> edge : v.getListAdj()) {
                Vertex<E> neighbor = edge.getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // Si todos los vértices fueron visitados, el grafo es conexo
        return visited.size() == listVertex.size();
    }


    public void bfs(E data) {
        Vertex<E> v = searchVertex(data);
        if (v == null) return;
        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        queue.add(v);
        visited.add(v);
        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.println(current.getData());
            for (Edge<E> e : current.getListAdj()) {
                Vertex<E> dest = e.getRefDest();
                if (!visited.contains(dest)) {
                    queue.add(dest);
                    visited.add(dest);
                }
            }
        }
    }

    public void insertEdgeWeight(E v, E z, int w) {
        Vertex<E> ori = searchVertex(v);
        Vertex<E> des = searchVertex(z);
        if (ori != null && des != null) {
            ori.getListAdj().add(new Edge<>(des, w));
            des.getListAdj().add(new Edge<>(ori, w)); // Porque es no dirigido
        }
    }

    public ArrayList<E> shortPath(E start, E end) {
        HashMap<Vertex<E>, Integer> dist = new HashMap<>();
        HashMap<Vertex<E>, Vertex<E>> prev = new HashMap<>();

        // Inicializar distancias a infinito para todos los vértices
        for (Vertex<E> v : listVertex) {
            dist.put(v, Integer.MAX_VALUE);  // Asignar "infinito" a todos los vértices
            prev.put(v, null);
        }

        // Verificar si el vértice de inicio existe
        Vertex<E> startVertex = searchVertex(start);
        if (startVertex == null) {
            System.out.println("El vértice de inicio no existe.");
            return new ArrayList<>();  // Retornar una lista vacía si no existe el vértice de inicio
        }

        dist.put(startVertex, 0);  // Establecer la distancia del vértice de inicio a 0

        // Cola de prioridad para implementar Dijkstra
        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<E> u = pq.poll();

            // Actualizar las distancias de los vecinos
            for (Edge<E> edge : u.getListAdj()) {
                Vertex<E> v = edge.getRefDest();
                int alt = dist.get(u) + edge.getWeight();  // Nueva distancia a v

                // Si se encuentra un camino más corto
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    pq.add(v);
                }
            }
        }

        // Verificar si el vértice de destino tiene una distancia válida
        Vertex<E> endVertex = searchVertex(end);
        if (endVertex == null || dist.get(endVertex) == Integer.MAX_VALUE) {
            System.out.println("No se encontró un camino desde " + start + " hasta " + end);
            return new ArrayList<>();  // Retornar una lista vacía si no hay camino
        }

        // Reconstruir el camino desde 'end' hasta 'start' usando el mapa prev
        ArrayList<E> path = new ArrayList<>();
        for (Vertex<E> at = endVertex; at != null; at = prev.get(at)) {
            path.add(0, at.getData());  // Insertar al principio para obtener el camino correcto
        }

        return path;
    }


    private void dfsRecursive(Vertex<E> v, Set<Vertex<E>> visited) {
        System.out.println(v.getData());
        visited.add(v);
        for (Edge<E> e : v.getListAdj()) {
            Vertex<E> dest = e.getRefDest();
            if (!visited.contains(dest)) {
                dfsRecursive(dest, visited);
            }
        }
    }

    public String toString() {
        return this.listVertex.toString();
    }
}
