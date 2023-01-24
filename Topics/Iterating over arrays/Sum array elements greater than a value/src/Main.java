import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeArray = scanner.nextInt();
        int[] array = new int[sizeArray];
        int element;
        int sum = 0;
        for (int i = 0; i < sizeArray; i++) {
            element = scanner.nextInt();
            array[i] = element;
        }
        int n = scanner.nextInt();
        for (int m : array) {
            if (m > n) {
                sum += m;
            }
        }
        System.out.println(sum);
    }
}
