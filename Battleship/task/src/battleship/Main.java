package battleship;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player();
        //Initialization of the ships
        Ship[] ships = {
                new Ship(5, "Aircraft Carrier"),
                new Ship(4, "Battleship"),
                new Ship(3, "Submarine"),
                new Ship(3, "Cruiser"),
                new Ship(2, "Destroyer")
        };
        //A game is initialized
        Battleship game = new Battleship();
        //It creates the matrix of the battlefield and prints it.
        game.startBattlefield();
        game.printField();
        //The ships are place in the battlefield
        for (Ship ship: ships) {
            game.placeShips(ship);
            game.printField();
        }
        System.out.println("\n" + "The game starts!" + "\n");

        player1.attack();
    }
}
