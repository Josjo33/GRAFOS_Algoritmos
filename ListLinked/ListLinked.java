package ListLinked;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListLinked<E> implements Iterable<E> {
    private Node<E> head;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    public boolean remove(E data) {
        Node<E> current = head, previous = null;
        while (current != null) {
            if (current.data.equals(data)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public boolean contains(E data) {
        for (E elem : this) {
            if (elem.equals(data))
                return true;
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;

            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E elem : this) {
            sb.append(elem.toString());
        }
        return sb.toString();
    }

    // Método size() para contar los elementos en la lista
    public int size() {
        int size = 0;
        Node<E> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // Agregar el método isEmpty() para verificar si la lista está vacía
    public boolean isEmpty() {
        return head == null;  // La lista está vacía si `head` es null
    }

    // Agregar el método get(int) para obtener un elemento por su índice
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
}
