package battleship;

import java.util.Scanner;

public class Battleship {
    String coor1;
    String coor2;
    int[][] coordinates = new int[2][2];
    Scanner scanner = new Scanner(System.in);
    String[][] field = new String[11][11];

    public void start() {
        int size = field.length - 1;
        //It creates the matrix for the field.
        for (int row = 0; row <= size; row++) {
            for (int i = 0; i <= size; i++) {
                if (row == 0 && i != 0) {
                    field[0][i] = String.valueOf(i);
                } else if (i == 0 && row != 0) {
                    field[row][i] = String.valueOf((char) (row + 64));
                } else {
                    field[row][i] = row == 0 && i == 0 ? null: "~";
                }
            }
            System.out.println("");
        }
        //Prints the field
        for (int row = 0; row <= size; row++) {
            for (int i = 0; i <= size; i++) {
                if (field[row][i] == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(" " + field[row][i]);
                }
            }
            System.out.println("");
        }
    }
    public void askForCoordinates() {

        String coor1 = scanner.next();
        String coor2 = scanner.next();
        //Verifies if the coordinates are in range.
        boolean inRange = (Check.hasValuesColumn(coor1.substring(0,1), field) && Check.hasValuesColumn(coor2.substring(0, 1), field)) && (Check.hasValuesRow(coor1.substring(1,2), field) && Check.hasValuesRow(coor2.substring(1, 2), field));
        if (inRange) {
            System.out.println("Good input");
        } else {
            System.out.println("Not a valid input");
        }
    }

}

class Check {
    public static boolean hasValuesRow(String letter, String[][] arr ) {
        for (int  element = 1; element <= arr.length - 1; element++) {
            if (arr[0][element].equals(letter)) {
                return true;
            }
        }
        return false;
    }
    public static boolean hasValuesColumn(String letter, String[][] arr ) {
        for (int element = 1; element <= arr.length - 1; element++) {
            if (arr[element][0].equals(letter)) {
                return true;
            }
        }
        return false;
    }
}