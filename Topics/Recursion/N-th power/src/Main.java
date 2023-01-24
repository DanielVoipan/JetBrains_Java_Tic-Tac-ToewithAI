import java.util.Scanner;

public class Main {

    public static double pow(double a, long n) {
        if (a % 2 == 0) {
            a = Math.pow((Math.pow(a,2)), (double) n / 2);
        } else {
            a = a * Math.pow(a, n - 1);
        }
        n = n / 2;
        if (n != 0) {
            pow(a, n);
        } else {
            return a;
        }
        return a;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final double a = Double.parseDouble(scanner.nextLine());
        final int n = Integer.parseInt(scanner.nextLine());
        System.out.println(pow(a, n));
    }
}