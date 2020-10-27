interface Double<T> extends Iterable<T>{
    // Inialisiasi akan mereset semua ke awal
    public void initialize();
    // Cek apakah kosong atau tidak
    public boolean isEmpty();
    // Mendaptakan ukuran atau panjang
    public int getSize();
    // Mendapatkan data satu persatu ke arah ekor -> next().data
    public Node<T> next();
    // Mendapatkan data satu persatu ke arah kepala -> prev().data
    public Node<T> prev();
    // Mendapatkan data berdasarkan index tertentu
    public T get(int index);
    // Mendapatkan data pada kepala
    public T getFirst();
    // Mendapatkan data pada ekor
    public T getLast();
    // Sama aja dengan addLast(), menambah data di ujung
    public void add(T data);
    // Menambah data pada index tertentu
    public void add(int index, T data);
    // Menambahkan data di awal
    public void addFirst(T data);
    // Menambahkan data di ujung
    public void addLast(T data);
    // Menambahkan data setelah elemen tau node tertentu
    public void addAfter(T key, T data);
    // Hapus berdasar index
    public void remove(int index);
    // Hapus berdasar value satu aja
    public void removeOne(T data);
    // Hapus berdasar value semua yang cocok
    public void removeAll(T data);
    // Hapus node atau elemen pertama
    public void removeFirst();
    // Hapus node atau elemen terakhir
    public void removeLast();
    // Genti elemen berdasar index
    public void replace(int index, T data);
    // Ganti elemen berdasar value, satu aja yg cocok
    public void replaceOne(T prev, T data);
    // Ganti elemen berdasr semua value yang cocok
    public void replaceAll(T prev, T data);
    // Menampilkan dalam bentuk string
    public String toString();
}