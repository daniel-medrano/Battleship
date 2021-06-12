import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] words = new String[4];
        for (int i = 0; i < words.length; i++) {
            words[i] = scanner.next();
            System.out.println(words[i]);
        }
    }
}