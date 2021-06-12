import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int[] data = new int[6];
        for (int i = 0; i < data.length; i++) {
            data[i] = scanner.nextInt();
        }

        int hourDifference = data[3] - data[0];
        int minDifference = data[4] - data[1];
        int secondsDifference = data[5] - data[2];
        if (secondsDifference < 0) {
            minDifference--;
            secondsDifference = secondsDifference + 60;
        }
        if (minDifference < 0) {
            hourDifference--;
            minDifference = minDifference + 60;
        }
        int result = ((hourDifference*3600)+(minDifference*60)+secondsDifference);
        System.out.println(result);

    }
}