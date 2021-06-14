package battleship;

import java.util.Scanner;

class Field {
    String[][] field = new String[11][11];
    int size = field.length - 1;

    public void createField() {
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
        //Prints the field to the user. Every time the user places a new ship in the matrix, the field is printed again.
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
}

public class Battleship {
    //Variables where the coordinates are.
    Scanner scanner = new Scanner(System.in);
    private static Field field = new Field();

    public void start() {
        field.createField();
        field.printField();
    }

    public void refresh() {
        field.printField();
    }
    public static Field getField() {
        return field;
    }

    public void placeShips(Ship ship) {
        System.out.printf("Enter the coordinates of the %s (%d cells): ", ship.name, ship.cells);
        do {
            askForCoordinates(ship);
        } while (ship.isDeployed == false);
        printShip(ship);
    }

    public void printShip(Ship ship) {
        int constant;
        int min;
        int max;
        if (ship.horizontal) {
            constant = ship.coordinates[0][0];
            if (ship.coordinates[0][1] < ship.coordinates[1][1]) {
                min = ship.coordinates[0][1];
                max = ship.coordinates[1][1];
            } else {
                min = ship.coordinates[1][1];
                max = ship.coordinates[0][1];
            }
            for (int c = min; c <= max; c++ ) {
                field.field[constant][c] = "O";
            }
        } else {
            constant = ship.coordinates[0][1];
            if (ship.coordinates[0][0] > ship.coordinates[1][0]) {
                min = ship.coordinates[1][0];
                max = ship.coordinates[0][0];
            } else {
                min = ship.coordinates[0][0];
                max = ship.coordinates[1][0];
            }
            for (int c = min; c <= max; c++ ) {
                field.field[c][constant] = "O";
            }
        }
    }

    //This method checks if you wanna place a ship to close from another
    public boolean checkZone(int[][] coordinates, boolean horizontal, boolean vertical) {
        int constant;
        int min;
        int max;
        //If horiOrVerti is 1, that means that the ship to be revise is horizontal.
        if (horizontal) {
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
                    if (field.field[constant][min - 1] == "O") {
                        return false;
                    }
                }
                if (max != 10) {
                    //This part revises if there´s an "O" in OOOOZ
                    if (field.field[constant][max + 1] == "O") {
                        return false;
                    }
                }
                if (constant != 10) {
                    //Revises if there are any other ships  or "O´s" under the ship.
                    if (field.field[constant + 1][c] == "O") {
                        return false;
                    }
                }
                if (constant != 1) {
                    //Revises if there are any other ships or "O´s" above the ship.
                    if (field.field[constant - 1][c] == "O") {
                        return false;
                    }
                }
            }
        //If horiOrVerti is 2, that means that the ship to be revise is vertical.
        } else if (vertical) {
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
                    if (field.field[min - 1][constant] == "O") {
                        return false;
                    }
                }
                if (max != 10) {
                    if (field.field[max + 1][constant] == "O") {
                        return false;
                    }
                }
                if (constant != 10) {
                    //Revises if there are any other ships  or "O´s" under the ship.
                    if (field.field[c][constant + 1] == "O") {
                        return false;
                    }
                }
                if (constant != 1) {
                    //Revises if there are any other ships or "O´s" above the ship.
                    if (field.field[c][constant - 1] == "O") {
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
        String coor1 = scanner.next().toUpperCase();
        String coor2 = scanner.next().toUpperCase();

        coordinates = Tools.toMatrix(coor1, coor2, field.field);
        //Verifies if the coordinates are in range.
        boolean inRange = ((coordinates[0][0] >= 1 && coordinates[0][0] <= 10) && ((coordinates[0][1] >= 1 && coordinates[0][1] <= 10)) && (coordinates[1][0] >= 1 && coordinates[1][0] <= 10) && (coordinates[1][1] >= 1 && coordinates[1][1] <= 10));
        if (inRange) {
            //With the method toMatrix the coordinates like F3 and F7 are converted to numbers like (6,3) and (6,7)
            boolean horizontal = (coordinates[0][0] == coordinates[1][0]) && (Math.abs(coordinates[0][1] - coordinates[1][1]) + 1 == ship.cells);
            boolean vertical = (coordinates[0][1] == coordinates[1][1]) && (Math.abs(coordinates[0][0] - coordinates[1][0]) + 1 == ship.cells);
            //This if avoids the ship from being diagonal, because both of the values of the input must have a common values, either the letter or the num, and when they´re subtracted one of the values should be 0
            if (horizontal) {
                //Points out that the ship will be horizontal.
                if (checkZone(coordinates, horizontal, vertical)) {
                    //The coordinates are assigned and gives permission to deploy the ship on the field
                    ship.deploy(coordinates, horizontal);
                } else {
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                }
            } else if (vertical) {
                //Points out that the ship will be vertical.
                if (checkZone(coordinates, horizontal, vertical)) {
                    //The coordinates are assigned and gives permission to deploy the ship on the field
                    ship.deploy(coordinates, horizontal);
                } else {
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                }
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
            }
        } else {
            System.out.println("Error! The coordinates don´t exist! Try again:");
        }
    }
}

class Player {
    String shot;
    int[] shotCoordinates;
    boolean inRange;
    Scanner scanner = new Scanner(System.in);
    Field field = Battleship.getField();
    public void attack() {
        System.out.println("\n" + "Take a shot!" + "\n");
        do {
            shot = scanner.next();
            int lenShot = shot.length();
            inRange = (Tools.hasValuesColumn(shot.substring(0, 1), field.field) && Tools.hasValuesRow(shot.substring(1, lenShot), field.field));
            if (!inRange) {
                System.out.println("\n" + "Error! You entered the wrong coordinates! Try again:" + "\n");
            }
        } while (!inRange);
        shotCoordinates = Tools.toArray(shot, field.field);
        if (field.field[shotCoordinates[0]][shotCoordinates[1]] == "O") {
            field.field[shotCoordinates[0]][shotCoordinates[1]] = "X";
            field.printField();
            System.out.println("\n" + "You hit a ship!" + "\n");
        } else {
            field.field[shotCoordinates[0]][shotCoordinates[1]] = "M";
            field.printField();
            System.out.println("\n" + "You missed!" + "\n");
        }
    }
}
class Ship {
    String name;
    int cells;
    boolean isDeployed = false;
    boolean horizontal = false;
    int[][] coordinates;
    Ship(int cells, String name) {
        this.name = name;
        this.cells = cells;
    }

    public void deploy(int[][] coordinates, boolean horizontal) {
        this.coordinates = coordinates;
        this.horizontal = horizontal;
        isDeployed = true;

    }
}