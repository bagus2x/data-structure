import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularDoubleLinkedList<T> implements Double<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public void initialize() {
        size = 0;
        head = tail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public Node<T> next() {
        return head;
    }

    public Node<T> prev() {
        return tail;
    }

    public T get(int index) {
        if( index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for(int i = 0; i < size; i++) {
            if(i == index) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    
    public T getFirst() {
        return head.data;
    }
    
    public T getLast() {
        return tail.data;
    }

    public void add(T data) {
        if (head == null) {
            head = tail =  new Node<T>(data);
        } else {
            Node<T> node = new Node<T>(tail, data, head);
            tail.next = node;
            head.prev = node;
            tail = node;
        }
        size++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(data);
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = new Node<>(current, data, current.next);
        size++;
    }

    public void addFirst(T data) {
        if (head == null) {
            head = tail = new Node<T>(data);
        } else {
            Node<T> node = new Node<T>(tail, data, head);
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void addLast(T data) {
        add(data);
    }

    public void addAfter(T key, T data) {
        if(head == null) {
            head = tail = new Node<T>(data);
            size++;
            return;
        }
        Node<T> current = head;
        for(int i = 0; i < size; i++) {
            if(current.data.equals(key)) {
                current.next = new Node<T>(current, data, current.next);
                if(i == size - 1) {
                    head.prev = tail = current.next;
                }
                size++;
                return;
            }
            current = current.next;
        }
    }

    public void remove(int index) {
        if( index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        if(index == 0) {
            removeFirst();
            return;
        }
        Node<T> current = head;
        for(int i = 0; i < size; i++) {
            if(i == index-1) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        size--;
    }

    public void removeOne(T data) {
        removeByValue(data, true);
    }

    public void removeAll(T data) {
        removeByValue(data, false);
    }

    
    public void removeFirst() {
        if(head == null) throw new NoSuchElementException();
        head = head.next;
        tail.next = head;
        head.prev = tail;
        size --;
    }

    public void removeLast() {
        if(head == null) throw new NoSuchElementException();
        tail = tail.prev;
        tail.next = head;
        head.prev = tail;
        size--;
    }
    
    private void removeByValue(T data, boolean once) {
        if(head.data.equals(data)) {
            removeFirst();
            if(once) return;
        }
        if(tail.data.equals(data)) {
            removeLast();
            if(once) return;
        }
        Node<T> current = head;
        while(current.next != null) {
            if(current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                if(once) return;
            }
            current = current.next;
        }
    }

    public void replace(int index, T data) {
        if( index < 0 || index > size - 1)  throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for(int i = 0; i < size; i++) {
            if(i == index) {
                current.data = data;
                break;
            }
            current = current.next;
        }
    }

    public void replaceOne(T prev, T data) {
       replaceByValue(prev, data, true);
    }

    public void replaceAll(T prev, T data) {
       replaceByValue(prev, data, false);
    }

    private void replaceByValue(T prev, T data, boolean once) {
        Node<T> current = head;
        for(int i = 0; i < size; i++) {
            if(current.data.equals(prev)) {
                current.data = data;
                if(once) break;
            }
            current = current.next;
        }
    }

    public String toString() {
        String res = "[";
        int i = 0;
        for(T e: this) {
            res += e;
            i++;
            if(i != getSize()) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private Node<T> current = head;
            private int i = 0;

            @Override
            public boolean hasNext() {
                return size != i++;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
