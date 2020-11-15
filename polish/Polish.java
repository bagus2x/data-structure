import java.util.LinkedList;
import java.util.Stack;

public class Polish {

    /* a. Ubah infix menjadi kebalikannya contoh A+B*C menjadi C*B+A.
     * b. Ubah ke postfix.
     * c. Reversi hasil dari postfix tadi.
    */
    public static LinkedList<String> InfixToPrefix(String infix) {
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
        postfix = InfixToPosfix(reversedInfix.toString());
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
    public static LinkedList<String> InfixToPosfix(String infix) {
        LinkedList<String> splittedInfix = splitInfix(infix);
        LinkedList<String> p = new LinkedList<>();
        Stack<String> operator = new Stack<>();
        splittedInfix.add(")");
        operator.add("(");

        for (String i : splittedInfix) {
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

    public static LinkedList<String> splitInfix(String infix) {
        LinkedList<String> splittedInfix = new LinkedList<>();
        for (String symbol : infix.split("")) {
            splittedInfix.add(symbol);
        }
        return splittedInfix;
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
