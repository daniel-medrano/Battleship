package battleship;

import java.util.Scanner;

public class Battleship {
    //Variables where the coordinates are.
    String coor1;
    String coor2;
    Scanner scanner = new Scanner(System.in);
    String[][] field = new String[11][11];
    int size = field.length - 1;

    public void start() {
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
    }

    public void printField() {
        //Prints the field
        for (int row = 0; row <= size; row++) {
            for (int i = 0; i <= size; i++) {
                if (field[row][i] == null) {
                    System.out.print(" ");
                } else if (i == 0 && row != 0){
                    System.out.print(field[row][i]);
                } else {
                    System.out.print(" " + field[row][i]);
                }
            }
            System.out.println("");
        }
    }

    public void printShips(Ship ship) {
        int constant;
        int min = 0;
        int max = 0;
        if (ship.sign == 1) {
            constant = ship.coordinates[0][0];
            if (ship.coordinates[0][1] < ship.coordinates[1][1]) {
                min = ship.coordinates[0][1];
                max = ship.coordinates[1][1];
            } else {
                min = ship.coordinates[1][1];
                max = ship.coordinates[0][1];
            }
            for (int c = min; c <= max; c++ ) {
                field[constant][c] = "O";
            }
        } else if (ship.sign == 2) {
            constant = ship.coordinates[0][1];
            if (ship.coordinates[0][0] < ship.coordinates[1][0]) {
                min = ship.coordinates[0][0];
                max = ship.coordinates[1][0];
            } else {
                min = ship.coordinates[1][0];
                max = ship.coordinates[0][0];
            }
            for (int c = min; c <= max; c++ ) {
                field[c][constant] = "O";
            }
        }
    }
    public void placeShips(Ship ship) {
        System.out.printf("Enter the coordinates of the %s (%d cells): ", ship.name, ship.cells);
        do {
            askForCoordinates(ship);
        } while (ship.sign == 0);
        printShips(ship);
    }

    public void askForCoordinates(Ship ship) {
        int[][] coordinates;
        //Requests the values
        String coor1 = scanner.next();
        String coor2 = scanner.next();
        //Verifies if the coordinates are in range.
        boolean inRange = (Tools.hasValuesColumn(coor1.substring(0,1), field) && Tools.hasValuesColumn(coor2.substring(0, 1), field)) && (Tools.hasValuesRow(coor1.substring(1,2), field) && Tools.hasValuesRow(coor2.substring(1, 2), field));
        if (inRange) {
            coordinates = Tools.toMatrix(coor1, coor2, field);
            int difference1 = Math.abs(coordinates[0][0] - coordinates[1][0]);
            int difference2 = Math.abs(coordinates[0][1] - coordinates[1][1]);
            //This if avoids the ship from being diagonal, because both of the values of the input must have a common values, either the letter or the num.
            //If structure to see if the size of the ship is correct.
            if (difference1 == 0 && (difference2 + 1 == ship.cells)) {
                ship.sign = 1;
                ship.coordinates = coordinates;
            } else if (difference2 == 0 && (difference1 + 1 == ship.cells)) {
                ship.sign = 2;
                ship.coordinates = coordinates;
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
            }
        } else {
            System.out.println("Not a valid input");
        }
    }
}

class Ship {
    String name;
    int cells = 0;
    int sign = 0;
    int[][] coordinates;
    Ship(int cells, String name) {
        this.name = name;
        this.cells = cells;
    }
    Ship(int[][] coordinates) {
        this.coordinates = coordinates;
    }
}

//A class with useful methods.
class Tools {
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
    //It creates a new matrix with coordinates.
    /*
    * Structure: coordinates = {
    * {letter, num}, A2
    * {letter, num}, A6
    * }
    * */
    public static int[][] toMatrix(String letter1, String letter2, String[][] arr) {
        int[][] matrix = new int[2][2];
        if (letter1.length() == 2)
            matrix[0][1] = Integer.parseInt(letter1.substring(1, 2));
        else
            matrix[0][1] = Integer.parseInt(letter1.substring(1, 3));
        for (int  e = 1; e <= arr.length - 1; e++) {
            if (arr[e][0].equals(letter1.substring(0, 1))) {
                matrix[0][0] = e;
            }
        }
        if (letter2.length() == 2)
            matrix[1][1] = Integer.parseInt(letter2.substring(1, 2));
        else
            matrix[1][1] = Integer.parseInt(letter2.substring(1, 3));
        for (int  e = 1; e <= arr.length - 1; e++) {
            if (arr[e][0].equals(letter2.substring(0, 1))) {
                matrix[1][0] = e;
            }
        }
        return matrix;
    }
    //Prints the matrix created before with the coordinates.
    public static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%4d", matrix[row][col]);
            }
            System.out.println();
        }
    }
}