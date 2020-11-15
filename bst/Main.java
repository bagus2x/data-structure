public class Main {
    public static void main(String[] args) {
        Tree t = new Tree();
        t.add(50);
        t.add(30);
        t.add(20);
        t.add(40);
        t.add(70);
        t.add(60);
        t.add(80);
        t.remove(40);
        t.remove(70);
        t.remove(80);
        System.out.println(t);
        t.setMode(Trasversal.PREORDER);
        System.out.println(t);
        t.setMode(Trasversal.POSTORDER);
        System.out.println(t);
        // int contains =  81;
        // System.out.printf("containes: %d %b\n", contains, t.contains(contains));
        // System.out.println(t);
    }
}
