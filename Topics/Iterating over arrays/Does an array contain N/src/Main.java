import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        int counter = 0;
        boolean isThere = false;
        while (counter < arraySize) {
            array[counter] = scanner.nextInt();
            counter++;
        }
        int number = scanner.nextInt();
        for (int c : array) {
            if (c == number) {
                isThere = true;
                break;
            }
        }
        System.out.println(isThere);
    }
}
