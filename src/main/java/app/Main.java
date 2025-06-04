package app;

import game.Game;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Character Duel Game!");
        
        Game game = new Game();
        game.start();
    }
}
