import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        System.out.println(Polish.InfixToPosfix(inp));;
        System.out.println(Polish.InfixToPrefix(inp));
        sc.close();
    }
}