package game;

import character.Character;
import character.Warrior;
import character.Archer;
import character.Mage;
import player.Player;
import game.Board;
import java.util.Scanner;

public class Game {

    public void start() {
        Scanner teclado = new Scanner(System.in);

        boolean bot = true;
        String player2Name = "CPU";

        System.out.println("Is the game multiplayer? (Yes or No)");
        String multiplayer = teclado.nextLine();
        if (multiplayer.equalsIgnoreCase("Yes")) {
            bot = false;

            System.out.println("Enter Player 2's nickname:");
            player2Name = teclado.nextLine();
        }

        System.out.println("Choose your character (Warrior, Mage or Archer):");
        String characterChoice = teclado.nextLine();

        System.out.println("Enter your nickname:");
        String nickname = teclado.nextLine();

        Character character;
        if (characterChoice.equalsIgnoreCase("Archer")) {
            character = new Archer(nickname);
        } else if (characterChoice.equalsIgnoreCase("Mage")) {
            character = new Mage(nickname);
        } else {
            character = new Warrior(nickname);
        }

        Player player1 = new Player(nickname, character, false);
        Player player2 = new Player(player2Name, new Archer("Legolas"), bot);

        System.out.println("Welcome, " + player1.getName() + "! You chose " + character.getClass().getSimpleName() + ".");
        System.out.println("Welcome, " + player2.getName() + "! Your opponent is ready.");
        System.out.println("Game started!");

        Board board = new Board();
        board.placeCharacter(player1.getCharacter(), 2, 2);
        board.placeCharacter(player2.getCharacter(), 5, 5);
        board.printBoard();

        teclado.close();
    }
}
