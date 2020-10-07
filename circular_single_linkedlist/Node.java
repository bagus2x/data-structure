public class Node<T> {
    protected Node<T> next;
    protected T data;

    Node() {}

    protected Node(T data) {
        this.data = data;
        this.next = this;
    }

    protected Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node<T> next() {
        return next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
