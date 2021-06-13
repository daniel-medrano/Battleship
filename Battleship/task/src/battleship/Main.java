package battleship;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        //Initialization of the ships
        Ship aircraftCarrier = new Ship(5, "Aircraft Carrier");
        Ship battleship = new Ship(4, "Battleship");
        Ship submarine = new Ship(3, "Submarine");
        Ship cruiser = new Ship(3, "Cruiser");
        Ship destroyer = new Ship(2, "Destroyer");

        Battleship game = new Battleship();

        game.startBattlefield();
        game.printField();
        game.placeShips(aircraftCarrier);
        game.printField();

        game.placeShips(battleship);
        game.printField();

        game.placeShips(submarine);
        game.printField();

        game.placeShips(cruiser);
        game.printField();

        game.placeShips(destroyer);
        game.printField();



    }
}
