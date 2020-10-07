public class Node<T> {
    Node<T> prev;
    Node<T> next;
    T data;

    Node() {}

    Node(T data) {
        this.data = data;
    }

    Node(Node<T> prev, T data, Node<T> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }
}