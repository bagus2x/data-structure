class Main {
    public static void main(String[] args) {
        Double<Integer> cdl = new CircularDoubleLinkedList<>();
        cdl.add(10);
        cdl.add(20);
        cdl.add(30);
        cdl.add(40);
        cdl.add(50);
        cdl.addAfter(50, 45);
        System.out.println(cdl);
        System.out.println(cdl.getLast());
    }
}