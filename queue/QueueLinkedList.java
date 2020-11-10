import java.util.Iterator;
import java.util.NoSuchElementException;

class Node<T> {
    Node<T> next;
    T data;

    Node(T data) {
       this.data = data;
    }

    Node(T data, Node<T> next) {
       this.data = data;
       this.next = next;
    }
}

class QueueLinkedList<T> implements Iterable<T> {
    private Node<T> head, tail;
    private int size;

    public void initialize() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public T dequeue() {
        if(!isEmpty()) {
            T data = head.data;
            removeFirst();
            return data;
        }
        throw new NoSuchElementException();
    }

    private void removeFirst() {
        Node<T> temp = head;
        if(head == tail) {
            head = tail = null;
        }else {
            temp = temp.next;
            head = temp;
        }
        size--;
    }

    public void enqueue(T n) {
        addLast(n);
    }

    private void addLast(T data) {
        Node<T> node = new Node<>(data); 
        if(isEmpty()) {
            head = tail = node;
        }else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T peek() {
        return head.data;
    }

    @Override
    public String toString() {
        String res = "[";
        int i = 0;
        for(T e : this) {
            res += e;
            i++;
            if(i != size) {
                res += ", ";
            }
        }
        return res + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;


            // Cek kondisi agar bisa berenti
            @Override
            public boolean hasNext() {
                return current != null;
            }

            // Mengambil value, dan berpindah elemnet
            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
            
        };
    }
}
