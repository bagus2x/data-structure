import java.util.Iterator;

public class CircularDoubleLinkedList<T> implements Iterable<T> {
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

    /*
        get(int index) <- pengaksesan berdasar index
        getFisrt() <- pengasesan value pertama
        getLast() <- pengaksesan value terakhir
    */

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

    /*
        add(T data) <- Menambahkan data di ekor
        add(int index, T data) <- Menambahkan data di index tertentu
        addFirst(T data) <- Menambahkan data di kepala
        addLast(T data) <- Menambahkan data di ikor
    */
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

    /*
        remove(int index) <- Penghapusan berdasarkan index
        removeOne(T data) <- Penghapusan satu aja berdasar value 
        removeAll(T data) <- Penghapusan semua sesuai dengan value parameter
        removeFirst() <- Penghapusan kepala
        removeLast() <- Penghapusan Ekor
    */
    public void removeOne(T data) {
        removeByValue(data, true);
    }

    public void removeAll(T data) {
        removeByValue(data, false);
    }

    
    public void removeFirst() {
        if(head == null) throw new IndexOutOfBoundsException();
        head = head.next;
        tail.next = head;
        head.prev = tail;
        size --;
    }

    public void removeLast() {
        if(head == null) throw new IndexOutOfBoundsException();
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

    /*
        replace(int index, T data) <- Replace berdasarkan index
        replaceOne(T prev, T data) <- Replace berdasarkan value sekali
        replaceAll(T prev, T data) <- Replace berdasarkan value semua
    */
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

    // Pengaksesan seluruh elemen dengan tipe kembalian Strin
    public String toString() {
        Node<T> current = head;
        String res = "[";
        for(int i = 0; i < size; i++) {
            res += current.data;
            current = current.next;
            if (i != size-1) {
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

            // Cek kondisi
            @Override
            public boolean hasNext() {
                var bool = size !=  i;
                i++;
                return bool;
            }

            // Berpindah antar value
            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
