import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] grades = new String[n];
        for (int i = 0; i < n; i++) {
            String grade = scanner.next();
            grades[i] = grade;
        }
        int nrA = 0;
        int nrB = 0;
        int nrC = 0;
        int nrD = 0;
        for (String grade : grades) {
            if ("A".equalsIgnoreCase(grade)) {
                nrA++;
            } else if ("B".equalsIgnoreCase(grade)) {
                nrB++;
            } else if ("C".equalsIgnoreCase(grade)) {
                nrC++;
            } else if ("D".equalsIgnoreCase(grade)) {
                nrD++;
            }
        }
        System.out.println(nrD + " " + nrC + " " + nrB + " " + nrA);
    }
}
