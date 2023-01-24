import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] yearlyIncome = new int[n];
        int[] individualTaxes = new int[n];
        double size = 0;
        for (int i = 0; i < n; i++) {
            yearlyIncome[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            individualTaxes[i] = scanner.nextInt();
        }
        int[][] array = new int[n][3];
        int comp = 1;
        for (int i = 0; i < n; i++) {
            array[i][0] = comp;
            comp++;
            for (int j = 0; j < yearlyIncome.length; j++) {
                array[j][1] =  yearlyIncome[j];
            }
            for (int k = 0; k < individualTaxes.length; k++) {
                array[k][2] =  individualTaxes[k];
            }
        }
        double max = 0;
        int c = 0;
        for (int i = 0; i < n; i++) {
            size = array[i][1] * (array[i][2] / 100.0f);
            if (size > max) {
                max = size;
                c = (int) array[i][0];
            } else if (size == max && c == 0) {
                max = size;
                c = (int) array[i][0];
            }
        }
        System.out.print(c);
    }
}
