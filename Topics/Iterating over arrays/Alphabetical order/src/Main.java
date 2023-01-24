import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String all = scanner.nextLine();
        String[] words = all.split("\\s+");
        int llength = words.length;
        boolean order = true;
        for (int i = 0; i < llength; i++) {
            if (i + 1 == llength) {
                continue;
            }
            if (words[i].compareTo(words[i + 1]) == 0) {
                continue;
            } else if (words[i].compareTo(words[i + 1]) < 0) {
                continue;
            } else {
                order = false;
            }
        }
        System.out.println(order);
    }
}
