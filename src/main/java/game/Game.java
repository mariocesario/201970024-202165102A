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
        characterAction(player1);
        if (!player2.isAlive()) {
            return;
        }

        System.out.println(player2.getName() + ", it's your turn!");
        characterAction(player2);
    }

    private void characterAction(Character c) {
        System.out.println("Enter the action: (A - Attack. M - Move. D - Defender. S - Special");
        char letter = scanner.nextLine().toUpperCase().charAt(0);

        if (letter == 'A') {
            characterAttack(c);
        } else if (letter == 'M') {
            characterMove(c);
        } else if (letter == 'D') {
            characterDefender(c);
        } else if (letter == 'S') {
            characterSpecial(c);
        } else {
            System.out.println("Invalid action! Try again.");
            characterAction(c);
        }
    }

    private void characterAttack(Character c) {
        board.printBoard();
    }

    private void characterDefender(Character c) {
        board.printBoard();
    }

    private void characterSpecial(Character c) {
        board.printBoard();
    }

    private void characterMove(Character c) {
        executeMove(c);
        board.printBoard();
    }

    private void executeMove(Character c) {
        int newRow = -1;
        int newCol = -1;
        int[] position;

        position = getNewPosition(c);
        newRow = position[0];
        newCol = position[1];

        boolean isValidMove = validateMove(c, newRow, newCol);

        if (!isValidMove) {
            characterMove(c);
        } else {
            board.moveCharacter(c, newRow, newCol);
        }
    }

    private boolean validateMove(Character c, int newRow, int newCol) {
        if (!board.isValidPosition(newRow, newCol)) {
            System.out.println("Invalid move! Position is out of bounds. Try again.");
            return false;
        } else if (!isMoveWithinRange(c, newRow, newCol)) {
            System.out.println("Invalid move! Position is beyond character's range. Try again.");
            return false;
        } else if (board.isOccupied(newRow, newCol)) {
            System.out.println("Invalid move! Position is already occupied. Try again.");
            return false;
        }
        return true;
    }

    private int[] getNewPosition(Character c) {
        if (isMultiplayer || c != player2) {
            return getUserPosition();
        } else {
            return getRandomPosition();
        }
    }

    private int[] getUserPosition() {
        try {
            System.out.println("Enter the new row (1-10):");
            int newRow = Integer.parseInt(scanner.nextLine()) - 1;

            System.out.println("Enter the new column (A-J):");
            char colLetter = scanner.nextLine().toUpperCase().charAt(0);
            int newCol = colLetter - 'A';

            return new int[]{newRow, newCol};
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numbers and letters correctly.");
            return getUserPosition();
        }
    }

    private int[] getRandomPosition() {
        Random rand = new Random();
        int newRow = rand.nextInt(10);
        int newCol = rand.nextInt(10);

        if (!board.isValidPosition(newRow, newCol) || board.isOccupied(newRow, newCol)) {
            return getRandomPosition();
        }

        return new int[]{newRow, newCol};
    }

    public boolean isMoveWithinRange(Character c, int newRow, int newCol) {
        int currentRow = c.getRow();
        int currentCol = c.getCol();
        int rowDiff = newRow - currentRow;
        int colDiff = newCol - currentCol;

        return (rowDiff * rowDiff + colDiff * colDiff) <= (c.getRange() * c.getRange());
    }
}
