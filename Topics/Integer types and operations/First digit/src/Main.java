import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int integer = scanner.nextInt();
        while (integer >= 10) {
            integer /= 10;
        }
        System.out.println(integer);
    }
}