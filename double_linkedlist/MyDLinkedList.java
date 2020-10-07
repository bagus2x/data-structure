import java.util.Iterator;

// Tubagus Saifulloh(195150201111019)

/*
    agar lebih mudah saya menganalogikan kelas == javascript objek
    node = {prev, data, next}

           A         B         C        D
    head = {null, 1, B} {A, 2, C} {B, 2, D} {C, 2, null}
*/

// Implement interface Iterable biar bisa di loop dgn for each
public class MyDLinkedList<T> implements Iterable<T> {
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
    
    public T getFirst() {
        return head.data;
    }
    
    public T getLast() {
        return tail.data;
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

    public void add(T data) {
        if (head == null) {
            head = tail =  new Node<>(data);
        } else {
            Node<T> node = new Node<T>(tail, data, null);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addLast(T data) {
        add(data);
    }

    public void addFirst(T data) {
        if (head == null) {
            head = tail = new Node<T>(data);
        } else {
            Node<T> node = new Node<T>(null, data, head);
            head = node;
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
        Node<T> node = new Node<>(current, data, current.next);
        current.next = node;
        size++;
    }

    public void remove(int index) {
        if( index < 0 || index > size - 1)  throw new IndexOutOfBoundsException();
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

    public void removeFirst() {
        if(head == null) throw new IndexOutOfBoundsException();
        head = head.next;
        size --;
    }

    public void removeLast() {
        if(head == null) throw new IndexOutOfBoundsException();
        tail.prev.next = null;
        size--;
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

    private void replaceByValue(T prev, T data, Boolean once) {
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
        Node<T> current = head;
        String res = "[";
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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
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
