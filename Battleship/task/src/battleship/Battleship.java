package battleship;

import java.util.Scanner;

public class Battleship {
    //Variables where the coordinates are.
    Scanner scanner = new Scanner(System.in);
    String[][] field = new String[11][11];
    int size = field.length - 1;

    public void startBattlefield() {
        //It creates the matrix for the field.
        for (int row = 0; row <= size; row++) {
            for (int i = 0; i <= size; i++) {
                if (row == 0 && i != 0) {
                    field[0][i] = String.valueOf(i);
                } else if (i == 0 && row != 0) {
                    field[row][i] = String.valueOf((char) (row + 64));
                } else {
                    //This part is for the coordinates (0, 0)
                    field[row][i] = row == 0 ? null: "~";
                }
            }
            System.out.println();
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
            System.out.println();
        }
    }

    public void printShip(Ship ship) {
        int constant;
        int min;
        int max;
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
        printShip(ship);
    }

    public boolean checkZone(int[][] coordinates, int horiOrVerti) {
        int constant;
        int min;
        int max;
        if (horiOrVerti == 1) {
            constant = coordinates[0][0];
            if (coordinates[0][1] < coordinates[1][1]) {
                min = coordinates[0][1];
                max = coordinates[1][1];
            } else {
                min = coordinates[1][1];
                max = coordinates[0][1];
            }
            //This for revises if there are any circles around the ship to be placed. This is for the horizontal.
            for (int c = min; c <= max; c++ ) {
                //The letter Z indicates zone
                if (min != 1) {
                    //This part revises if there´s an "O" in ZOOOO
                    if (field[constant][min - 1] == "O") {
                        return false;
                    }
                }
                if (max != 10) {
                    //This part revises if there´s an "O" in OOOOZ
                    if (field[constant][max + 1] == "O") {
                        return false;
                    }
                }
                if (constant != 10) {
                    //Revises if there are any other ships  or "O´s" under the ship.
                    if (field[constant + 1][c] == "O") {
                        return false;
                    }
                }
                if (constant != 1) {
                    //Revises if there are any other ships or "O´s" above the ship.
                    if (field[constant - 1][c] == "O") {
                        return false;
                    }
                }
            }
        } else if (horiOrVerti == 2) {
            constant = coordinates[0][1];
            if (coordinates[0][0] < coordinates[1][0]) {
                min = coordinates[0][0];
                max = coordinates[1][0];
            } else {
                min = coordinates[1][0];
                max = coordinates[0][0];
            }
            for (int c = min; c <= max; c++ ) {
                if (min != 1) {
                    if (field[min - 1][constant] == "O") {
                        return false;
                    }
                }
                if (max != 10) {
                    if (field[max + 1][constant] == "O") {
                        return false;
                    }
                }
                if (constant != 10) {
                    //Revises if there are any other ships  or "O´s" under the ship.
                    if (field[c][constant + 1] == "O") {
                        return false;
                    }
                }
                if (constant != 1) {
                    //Revises if there are any other ships or "O´s" above the ship.
                    if (field[c][constant - 1] == "O") {
                        return false;
                    }
                }
            }
        }
        //If it goes through the filter it returns a true.
        return true;
    }

    public void askForCoordinates(Ship ship) {
        int[][] coordinates;
        //Requests the values
        int horiOrVerti = 0;
        String coor1 = scanner.next();
        String coor2 = scanner.next();
        //Verifies if the coordinates are in range.
        boolean inRange = (Tools.hasValuesColumn(coor1.substring(0,1), field) && Tools.hasValuesColumn(coor2.substring(0, 1), field)) && (Tools.hasValuesRow(coor1.substring(1,2), field) && Tools.hasValuesRow(coor2.substring(1, 2), field));
        if (inRange) {
            coordinates = Tools.toMatrix(coor1, coor2, field);
            int difference1 = Math.abs(coordinates[0][0] - coordinates[1][0]);
            int difference2 = Math.abs(coordinates[0][1] - coordinates[1][1]);
            //This if avoids the ship from being diagonal, because both of the values of the input must have a common values, either the letter or the num.
            //todo 1. Introduce here another if to corroborate if the coordinates of the ship to be revised are not on the "path" of the ones from another ship
            if (difference1 == 0 && (difference2 + 1 == ship.cells)) {
                //Points out that the ship will be horizontal.
                horiOrVerti = 1;
                if (checkZone(coordinates, horiOrVerti)) {
                    ship.sign = 1;
                    ship.coordinates = coordinates;
                } else {
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                }
            } else if (difference2 == 0 && (difference1 + 1 == ship.cells)) {
                //Points out that the ship will be vertical.
                horiOrVerti = 2;
                if (checkZone(coordinates, horiOrVerti)) {
                    ship.sign = 2;
                    ship.coordinates = coordinates;
                } else {
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                }
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
    int cells;
    int sign = 0;
    int[][] coordinates;
    Ship(int cells, String name) {
        this.name = name;
        this.cells = cells;
    }
}