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
        // Completar
        if (searchVertex(data) == null) {
            listVertex.add(new Vertex<>(data));
        }

    }

    public void insertEdge(E verOri, E verDes) {
        // Completar
        Vertex<E> vOri = searchVertex(verOri);
        Vertex<E> vDes = searchVertex(verDes);
        if (vOri != null && vDes != null) {
            vOri.getListAdj().add(new Edge<>(vDes));
        }
    }

    // Metodo buscar Vertice
    public Vertex<E> searchVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data))
                return v;
        }
        return null;
    }

    // Metodo Buscar arista
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

    // Metodos eliminar Vertice y arista
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

    // Metodo de recorrido dfs que muestra los vertices visitados
    public void dfs(E data) {
        Vertex<E> v = searchVertex(data);
        if (v == null)
            return;
        Set<Vertex<E>> visited = new HashSet<>();
        dfsRecursive(v, visited);
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
