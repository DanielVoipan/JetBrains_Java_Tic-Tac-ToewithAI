import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        int[] lNum = new int[num];
        int counter = 0;
        String[] str = scanner.nextLine().split(" ");
        for (String i : str) {
            lNum[counter] = Integer.parseInt(i);
            counter++;
        }
        System.out.println(Arrays.stream(lNum).sum());
    }
}