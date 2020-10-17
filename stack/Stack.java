import java.util.EmptyStackException;

class Stack<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public void initialize() {
        head = tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T data) {
        Node<T> node = new Node<T>(data);
        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }

    @SafeVarargs
    final public void push(T... data) {
        for (T d : data) {
            push(d);
        }
    }

    public void unshift(T data) {
        if (size == 0) {
            head = tail = new Node<T>(data);
        } else {
            head = new Node<>(data, head);
        }
        size++;
    }

    @SafeVarargs
    final public void unshift(T... data) {
        for (int i = data.length - 1; i >= 0; i--) {
            unshift(data[i]);
        }
    }

    public T shift() {
        if (size == 0)
            new EmptyStackException();
        T shiftData = head.data;
        removeFirst();
        return shiftData;
    }

    public void removeFirst() {
        if (size == 0)
            new EmptyStackException();
        head = head.next;
        size--;
    }

    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        T popData = tail.data;
        removeLast();
        return popData;
    }

    public void removeLast() {
        if(head == null) throw new EmptyStackException();
        if(head.next == null) {
            head = tail = null;
        }else {
            Node<T> current =  head;
            while(current.next.next != null) {
                current =  current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
    }

    @Override
    public String toString() {
        String res = "[";
        Node<T> current = head;
        while (current != null) {
            res += current.data;
            current = current.next;
            if (current != null) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }
}