package battleship;

import java.util.Arrays;

public class Battleship {

    String[][] field = new String[11][11];

    public void start() {
        int size = field.length - 1;

        for (int row = 0; row <= size; row++) {
            for (int i = 0; i <= size; i++) {
                if (row == 0 && i != 0) {
                    field[0][i] = " " + i;
                } else if (i == 0 && row != 0) {
                    field[row][i] = " " + (char) (row + 64);
                } else {
                    field[row][i] = row == 0 && i == 0 ? " ": " ~";
                }
            }
            System.out.println("");
        }

        for (int row = 0; row <= size; row++) {
            for (int i = 0; i <= size; i++) {
                System.out.print(field[row][i]);
            }
            System.out.println("");
        }
    }

    public void coordinatesReceiver() {

    }
}
