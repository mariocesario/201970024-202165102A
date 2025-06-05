package game;

import character.Character;
import character.Warrior;
import character.Archer;
import character.Mage;
import java.util.Scanner;
import java.util.Random;

public class Game {

    private Board board;
    private Scanner scanner;
    private Character player1;
    private Character player2;
    private boolean isMultiplayer;

    public Game() {
        board = new Board();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Multiplayer? (Yes or No):");
        isMultiplayer = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.println("Choose your character (Warrior, Mage, or Archer):");
        String characterChoice = scanner.nextLine();
        System.out.println("Enter your nickname:");
        String nickname = scanner.nextLine();

        if (characterChoice.equalsIgnoreCase("Archer")) {
            player1 = new Archer(nickname);
        } else if (characterChoice.equalsIgnoreCase("Mage")) {
            player1 = new Mage(nickname);
        } else {
            player1 = new Warrior(nickname);
        }

        if (isMultiplayer) {
            System.out.println("Choose Player 2's character(Warrior, Mage, or Archer):");
            String characterChoice2 = scanner.nextLine();
            System.out.println("Enter Player 2's nickname:");
            String nickname2 = scanner.nextLine();

            if (characterChoice2.equalsIgnoreCase("Archer")) {
                player2 = new Archer(nickname2);
            } else if (characterChoice2.equalsIgnoreCase("Mage")) {
                player2 = new Mage(nickname2);
            } else {
                player2 = new Warrior(nickname2);
            }
        } else {
            player2 = new Archer("CPU");
        }

        board.placeCharacter(player1, 2, 2);
        board.placeCharacter(player2, 5, 5);
        board.printBoard();
        runGame();
    }

    private void runGame() {
        if (player1.isAlive() && player2.isAlive()) {
            runTurn();
            runGame();
        }

        System.out.println("Game over!");
    }

    private void runTurn() {
        System.out.println(player1.getName() + ", it's your turn!");
        moveCharacter(player1);
        if (!player2.isAlive()) {
            return;
        }

        System.out.println(player2.getName() + ", it's your turn!");
        moveCharacter(player2);
    }

    private void moveCharacter(Character c) {
        int newRow = -1;
        int newCol = -1;
        boolean validMove = false;

        while (!validMove) {
            if (isMultiplayer || c != player2) {
                try {
                    System.out.println("Enter the new row (1-10):");
                    newRow = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number for the row.");
                    continue; // Volta para pedir uma entrada válida
                }

                System.out.println("Enter the new column (A-J):");
                char colLetter = scanner.nextLine().toUpperCase().charAt(0);
                newCol = colLetter - 'A';
            } else {
                Random rand = new Random();
                do {
                    newRow = rand.nextInt(10);
                    newCol = rand.nextInt(10);
                } while (!board.isValidPosition(newRow, newCol) || board.isOccupied(newRow, newCol));
            }

            if (!board.isValidPosition(newRow, newCol)) {
                System.out.println("Invalid move! Position is out of bounds. Try again.");
            } else if (!isMoveWithinRange(c, newRow, newCol)) {
                System.out.println("Invalid move! Position is beyond character's range. Try again.");
            } else if (board.isOccupied(newRow, newCol)) {
                System.out.println("Invalid move! Position is already occupied. Try again.");
            } else {
                validMove = true;
            }

        }

        board.moveCharacter(c, newRow, newCol);
        board.printBoard();
    }

    public boolean isMoveWithinRange(Character c, int newRow, int newCol) {
        int currentRow = c.getRow();
        int currentCol = c.getCol();
        int rowDiff = newRow - currentRow;
        int colDiff = newCol - currentCol;

        return (rowDiff * rowDiff + colDiff * colDiff) <= (c.getRange() * c.getRange());
    }
}
