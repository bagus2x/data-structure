public class Node<T> {
    protected Node<T> prev;
    protected Node<T> next;
    protected T data;

    protected Node() {}

    protected Node(T data) {
        this.prev = this;
        this.data = data;
        this.next = this;
    }

    protected Node(Node<T> prev, T data, Node<T> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }

    public Node<T> next() {
        return next;
    }

    public Node<T> prev() {
        return prev;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
