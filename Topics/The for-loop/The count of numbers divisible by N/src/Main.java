import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] spltString = scanner.nextLine().split(" ");
        int fNum = Integer.parseInt(spltString[0]);
        int lNum = Integer.parseInt(spltString[1]);
        int div = Integer.parseInt(spltString[2]);
        int counter = 0;
        for (int i = fNum; i <= lNum; i++) {
            if (i % div == 0) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}