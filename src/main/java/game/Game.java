package game;

import character.Warrior;
import character.Archer;
import player.Player;
import game.Board;

public class Game {
    public void start() {
        System.out.println("Game started!");

        
        Board board = new Board();

        Warrior warrior = new Warrior("Thor");
        Archer archer = new Archer("Legolas");

       
       Player player1 = new Player("Mário", warrior, false);
       Player player2 = new Player("CPU", archer, true);

       
        board.placeCharacter(warrior, 2, 2);
        board.placeCharacter(archer, 5, 5);

        
        board.printBoard();
    }
}
