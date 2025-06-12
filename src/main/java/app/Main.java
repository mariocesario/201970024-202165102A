package app;

import game.Game;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean playAgain = true;
        while (playAgain) {
            Scanner teclado = new Scanner(System.in);
            Game game = new Game();
            game.start();

            System.out.println("Do you want to play again? (Yes or No)");
            String response = teclado.nextLine();
            playAgain = response.equalsIgnoreCase("Yes");
        }
    }
}
