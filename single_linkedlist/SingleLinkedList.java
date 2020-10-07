class Node<T> {
    T data;
    Node<T> next;

    Node() {}

    Node(T data) {
        this.data = data;
    }
}

// SingleLinkedList menggunakan generic jadi bisa menggunakan semua tipe data references/ UDT
// Contoh: Float, Integer, Sepatu dll
public class MyLinkedList<T> {
    private Node<T> head;
    private int size;

    public void initialize() {
        head = null;
        size = 0;
    }

    // Method getSize merupakan getter untuk mengakses atribut size
    public int getSize() {
        return size;
    }

    // Method isEmpty untuk menegecek apakah container ini kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Mendapatkan value berdasar index
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

    /*
        Ada empat jenis method 'add'
        -> add(int index, T data), tambah setelah index tertentu
        -> addOneAfter(T prev, T data), tambah setelah value tertentu
        -> addLast(T data) atau sama dengan add(T data), tambah ke paling akhir
        -> addFirst(T data), tambah ke paling awal
    */
    public void add(int index, T data) {
        if(index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        if(index == 0) {
            addFirst(data);
            return;
        }
        Node<T> node = new Node<T>(data);
        Node<T> current =  head;
        for(int i=0; i < index-1; i++) {
            current =  current.next;
        }
        node.next = current.next;
        current.next = node;
        size++;
    }

    public void add(T data) {
        Node<T> node = new Node<T>(data);
        if(head == null) {
            head = node;
        }else {
            Node<T> current =  head;
            while(current.next != null) {
                current =  current.next; // jika sampai loop terakhir, berarti current =  {data: value, next: null}
            }
            current.next = node;
        }
        size++;
    }

    public void addFirst(T data) {
        Node<T> node = new Node<T>(data);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(T data) {
        add(data);
    }

    public void addOneAfter(T prev, T data) {
        Node<T> node = new Node<>(data);
        if(head.data == prev) {
            add(1, data);
        }else {
            Node<T> current = head;
            while(current.next != null) {
                current = current.next;
                if(current.data.equals(prev)) {
                    node.next = current.next;
                    current.next = node;
                    break;
                }
            }
        }
        size++;
    }

    /*
        Ada tiga jenis method replace
        -> replace(int index, T data), replace berdasarkan index ternetu
        -> replaceOne(T before, T after), replace satu node berdasarkan value tertentu
        -> replaceAll(T before, T after), replace semua node berdasarkan value tertentu
    */
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

    /*
        Ada tiga jenis method remove
        -> remove(int index), remove berdasar index
        -> removeFirst(), remove kepala
        -> removeLast(), ekor
    */
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
            current =  current.next; // current =  ekor. dan buntut ekor selalu null
            if(current != null) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }
}
