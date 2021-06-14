package battleship;


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
        //In case the string with coordinates has three elements, it would save the number with two digits: 10
        if (letter1.length() == 2)
            matrix[0][1] = Integer.parseInt(letter1.substring(1, 2));
        else
            matrix[0][1] = Integer.parseInt(letter1.substring(1, 3));
        //Makes match with an element of the column 0 and the index of that match is saved.
        for (int  e = 1; e <= arr.length - 1; e++) {
            if (arr[e][0].equals(letter1.substring(0, 1))) {
                matrix[0][0] = e;
            }
        }
        //In case the string with coordinates has three elements, it would save the number with two digits: 10
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
    public static int[] toArray(String letter1, String[][] arr) {
        int[] matrix = new int[2];
        //In case the string with coordinates has three elements, it would save the number with two digits: 10
        if (letter1.length() == 2)
            matrix[1] = Integer.parseInt(letter1.substring(1, 2));
        else
            matrix[1] = Integer.parseInt(letter1.substring(1, 3));
        //Makes match with an element of the column 0 and the index of that match is saved.
        for (int  e = 1; e <= arr.length - 1; e++) {
            if (arr[e][0].equals(letter1.substring(0, 1))) {
                matrix[0] = e;
            }
        }
        return matrix;
    }
}