class Node<T> {
    T data;
    Node<T> next;

    Node() {}

    Node(T data) {
        this.data = data;
    }

    Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}

public class SingleLinkedList<T> {
    private Node<T> head;
    private int size;

    public void initialize() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if(index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        Node<T> current =  head;
        T res = null;
        for(int i=0; i< size; i++) {
            if(i == index) {
                res = current.data;
                break;
            }
            current =  current.next;
        }
        return res;
    }

    public void add(int index, T data) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        if(index == 0) {
            addFirst(data);
            return;
        }
        Node<T> current =  head;
        for(int i=0; i < index-1; i++) {
            current =  current.next;
        } 
        current.next = new Node<T>(data, current.next);
        size++;
    }

    public void add(T data) {
        Node<T> node = new Node<T>(data);
        if(head == null) {
            head = node;
        }else {
            Node<T> current =  head;
            while(current.next != null) {
                current =  current.next;
            }
            current.next = node;
        }
        size++;
    }

    public void addFirst(T data) {;
        head = new Node<T>(data, head);
        size++;
    }

    public void addLast(T data) {
        add(data);
    }

    public void addOneAfter(T prev, T data) {
        if(head.data == prev) {
            add(1, data);
        }else {
            Node<T> current = head;
            while(current.next != null) {
                current = current.next;
                if(current.data.equals(prev)) {
                    current.next = new Node<>(data, current.next);
                    break;
                }
            }
        }
        size++;
    }

    public void replace(int index, T data) {
        if(index > size - 1) throw new IndexOutOfBoundsException();
        Node<T> current =  head;
        for(int i=0; i < size; i++) {
            if(i == index) {
                current.data = data;
                break;
            }
            current =  current.next;
        }
    }

    public void replaceOne(T before, T after) {
        Node<T> current =  head;
        while(current != null) {
            if(current.data.equals(before)){
                current.data = after;
                return;
            }
            current =  current.next;
        }
    }

    public void replaceAll(T before, T after) {
        Node<T> current =  head;
        while(current != null) {
            if(current.data.equals(before)){
                current.data = after;
            }
            current =  current.next;
        }
    }

    public void remove(int index) {
        if(index > size - 1 || index <= 0) throw new IndexOutOfBoundsException();
        Node<T> current =  head;
        for(int i=0; i<index - 1; i++) {
            current =  current.next;
        }
        current.next = current.next.next;
        size--;
    }

    public void removeByValue(T data) {
        if(head.data.equals(data)) {
            head.next = head.next.next;
            size--;
        }
        Node<T> current = head;
        while(current.next != null) {
            if(current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
            }
            current = current.next;
        }
    }

    public void removeFirst() {
        if(head == null) throw new IndexOutOfBoundsException();
        head = head.next;
        size--;
    }

    public void removeLast() {
        if(head.next == null) {
            head = null;
        }else {
            Node<T> current =  head;
            while(current.next.next != null) {
                current =  current.next;
            }
            current.next = null;
        }
        size--;
    }

    public String toString() {
        Node<T> current =  head;
        String res = "[";
        while(current != null) {
            res += current.data;
            current =  current.next;
            if(current != null) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }
}
