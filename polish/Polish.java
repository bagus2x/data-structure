import java.util.LinkedList;
import java.util.Stack;

public class Polish {

    /* a. Ubah infix menjadi kebalikannya contoh A+B*C menjadi C*B+A.
     * b. Ubah ke postfix.
     * c. Reversi hasil dari postfix tadi.
    */
    public static LinkedList<String> infixToPrefix(String infix) {
        StringBuilder reversedInfix = new  StringBuilder();
        LinkedList<String> prefix = new LinkedList<>();
        LinkedList<String> postfix;
        for(int i = 0; i < infix.length(); i++) {
            char symbol = infix.charAt(i);
            if(symbol == '(') {
                symbol = ')';
            } else if(symbol == (')')) {
                symbol = '(';
            }
            reversedInfix.insert(0, Character.toString(symbol)); // dibalik
        }
        postfix = infixToPosfix(reversedInfix.toString());
        for(String p: postfix) {
            prefix.addFirst(p);
        }
        return prefix;
    }
    /*
     * Q = A+(B*C-(D/E^F)*G)*H
     * a. Push tanda “(“ ke stack dan tambahkan tanda “)” di sentinel di Q. 
     * b. Scan Q dari kiri ke kanan, kemudian ulangi langkah c s.d f untuk setiap elemen Q
     *    sampai stack Q kosong.
     * c. Jika yang discan adalah operand, maka tambahkan ke P
     * d. Jika yang discan adalah “(“ maka push ke stack 5. Jika yang discan 
     *    adalah “)” maka pop isi stack sampai ditemukan tanda “(“, kemudian tambahkan
     *    ke P sedangkan tanda “(“ tidak disertakanke P.
     * e. Jika yang discan adalah
     *    operator, maka : - Jika elemen paling atas dari stack adalah operator yang
     *    mempunyai tingkatan sama atau lebih tinggi dari operator yang discan, maka
     *    pop operator tsb dan tambahkan ke P. - Push operator tersebut ke stack.
     * f. Keluar
     */
    public static LinkedList<String> infixToPosfix(String infix) {
        LinkedList<String> p = new LinkedList<>();
        Stack<String> operator = new Stack<>();
        infix += ")";
        operator.add("(");

        for (String i : infix.split("")) {
            if (i.matches("[+*^/-]")) { // test buat coba2 regex https://regexr.com/
                if (getPriotity(operator.peek()) >= getPriotity(i)) {
                    p.add(operator.pop());
                }
                operator.push(i);
            } else if (i.equals("(")) {
                operator.push(i);
            } else if (i.equals(")")) {
                while (true) {
                    if (operator.peek().equals("(")) {
                        operator.pop();
                        break;
                    }
                    p.add(operator.pop());
                }
            } else {
                p.add(i);
            }
        }
        return p;
    }

    /*
     * 1.Tambahkan tanda “)” pada sentinel di P 
     * 2.Scan P dari kiri ke kanan, ulangi
     *   langkah c dan d untuk setiap elemen P sampai ditemukan sentinel. 
     * 3.Jika yang
     *   discan adalah operand, maka push ke stack.
     * 4.Jika yang discan adalah operator
     *   (sebut opr1), maka  Pop 1 buah elemen teratas dari stack, simpan dalam
     *   variable var1.  Pop 1 buah elemen teratas dari stack, simpan dalam variable
     *   var2.  Hitung variable (var2 opr1 var1), simpan hasil di variable hitung. 
     *   Push variable hitung ke stack. 
     * 5.Pop isi stack dan simpan di variable value.
     * 6.Keluar.
     */
    public static String postfixToInfix(String postfix) {
        Stack<String> stack = new Stack<>();
        String value;
        for(String s: postfix.split("")) {
            if(getPriotity(s) == -1) {
                stack.push(s);
            }else {
                String var1 = stack.pop();
                String var2 = stack.pop();
                String res = "(" + var2 + s + var1 + ")";
                stack.push(res);
            }
        }
        value = stack.pop();
        return value;
    }

    public static String prefixToInfix(String prefix) {
        Stack<String> stack = new Stack<>();
        String splittedPrefix[] = prefix.split("");
        String value;
        for(int i = splittedPrefix.length - 1; i >= 0; i--) {
            if(getPriotity(splittedPrefix[i]) != -1) {
                String var1 = stack.pop();
                String var2 = stack.pop();
                String res = "(" + var1 + splittedPrefix[i] + var2 + ")";
                stack.push(res);
            }else {
                stack.push(splittedPrefix[i]);
            }
        }
        value = stack.pop();
        return value;
    }

    static int getPriotity(String symbol) {
        switch (symbol) {
            case "-":
            case "+":
                return 1;
            case "/":
            case "*":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }
}
