class Main {
    public static void main(String[] args) {
        MyDLinkedList<Integer> mdl = new MyDLinkedList<>();
        mdl.add(100);
        mdl.add(200);
        mdl.add(300);
        mdl.add(400);
        mdl.add(500);
        mdl.add(600);
        mdl.add(700);
        mdl.add(0, 50);
        mdl.add(5, 666);
        mdl.addLast(700);
        mdl.addLast(600);
        // System.out.println(mdl);
        mdl.replace(2, 350);
        mdl.replaceAll(700, 750);
        // mdl.replaceOne(600, 650);
        System.out.println(mdl);
        // mdl.removeLast();
        mdl.removeAll(600);
        System.out.println(mdl);
        // System.out.println(mdl.get(mdl.getSize()-1));
        // System.out.println(mdl.getFirst() + " " + mdl.getLast());
    }
}


/*
    {prev: null, data: null, next: null} <- init
    {prev: null, data: 100, next: null} < addLast(100)
*/