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
            String[] characterOptions = {"Warrior", "Mage", "Archer"};
            Random rand = new Random();
            String randomChoice = characterOptions[rand.nextInt(characterOptions.length)];

            if (randomChoice.equalsIgnoreCase("Archer")) {
                player2 = new Archer("CPU");
            } else if (randomChoice.equalsIgnoreCase("Mage")) {
                player2 = new Mage("CPU");
            } else {
                player2 = new Warrior("CPU");
            }

        }

        board.placeCharacter(player1, 2, 2);
        board.placeCharacter(player2, 5, 5);
        board.printBoard(player1, player2);
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
        boolean validAction;

        System.out.println(player1.getName() + ", it's your turn!");
        do {
            validAction = characterAction(player1);
        } while (!validAction);

        board.printBoard(player1, player2);

        if (!player2.isAlive()) {
            return;
        }

        System.out.println(player2.getName() + ", it's your turn!");
        do {
            validAction = characterAction(player2);
        } while (!validAction);

        board.printBoard(player1, player2);
    }

    private boolean characterAction(Character currentCharacter) {
        System.out.println("Enter the action: (A - Attack. M - Move. D - Defender. S - Special");
        char letter = scanner.nextLine().toUpperCase().charAt(0);

        if (letter == 'A') {
            characterAttack(currentCharacter);
        } else if (letter == 'M') {
            characterMove(currentCharacter);
        } else if (letter == 'D') {
            characterDefender(currentCharacter);
        } else if (letter == 'S') {
            characterSpecial(currentCharacter);
        } else {
            System.out.println("Invalid action! Try again.");
            return false;
        }

        return true;
    }

    private void characterAttack(Character currentCharacter) {
        Character opponent = getOpponent(currentCharacter);
        boolean isRangeForAttack = currentCharacter.checkAttackRange(opponent);
        if (!isRangeForAttack) {
            return;
        }
        opponent.takeDamage(currentCharacter.getAtk());
    }

    private void characterDefender(Character currentCharacter) {
        currentCharacter.defender();
    }

    private void characterSpecial(Character currentCharacter) {
        Character opponent = getOpponent(currentCharacter);
        currentCharacter.useSpecialPower(opponent);
    }

    private void characterMove(Character currentCharacter) {
        executeDirectionalMove(currentCharacter);
        board.printBoard(player1, player2); // Print após cada movimento
    }

    private void executeDirectionalMove(Character currentCharacter) {
        int maxRange = currentCharacter.getRange();
        int startRow = currentCharacter.getRow();
        int startCol = currentCharacter.getCol();
        int currentRow = startRow;
        int currentCol = startCol;
        int movesLeft = maxRange;

        while (movesLeft > 0) {
            System.out.println("Enter direction: C (Up), B (Down), E (Left), D (Right)");
            char direction = scanner.nextLine().toUpperCase().charAt(0);

            int newRow = currentRow;
            int newCol = currentCol;

            if (direction == 'C') {
                newRow -= 1;
            } else if (direction == 'B') {
                newRow += 1;
            } else if (direction == 'E') {
                newCol -= 1;
            } else if (direction == 'D') {
                newCol += 1;
            } else {
                System.out.println("Invalid direction! Try again.");
                continue;
            }

            if (!validateMove(currentCharacter, newRow, newCol)) {
                System.out.println("Invalid move! Try another direction.");
                continue;
            }
            
            board.moveCharacter(currentCharacter, newRow, newCol);
            currentRow = newRow;
            currentCol = newCol; 
            movesLeft--; 

            board.printBoard(player1, player2);

        }

        System.out.println(currentCharacter.getName() + " reached max movement range. Turn ended.");
    }

    private boolean validateMove(Character currentCharacter, int newRow, int newCol) {
        if (!board.isValidPosition(newRow, newCol)) {
            System.out.println("Invalid move! Position is out of bounds. Try again.");
            return false;
        } else if (!isMoveWithinRange(currentCharacter, newRow, newCol)) {
            System.out.println("Invalid move! Position is beyond character's range. Try again.");
            return false;
        } else if (board.isOccupied(newRow, newCol)) {
            System.out.println("Invalid move! Position is already occupied. Try again.");
            return false;
        }
        return true;
    }

    private int[] getNewPosition(Character currentCharacter) {
        if (isMultiplayer || currentCharacter != player2) {
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

    public boolean isMoveWithinRange(Character currentCharacter, int newRow, int newCol) {
        int currentRow = currentCharacter.getRow();
        int currentCol = currentCharacter.getCol();
        int rowDiff = newRow - currentRow;
        int colDiff = newCol - currentCol;

        return (rowDiff * rowDiff + colDiff * colDiff) <= (currentCharacter.getRange() * currentCharacter.getRange());
    }

    private Character getOpponent(Character currentCharacter) {
        return currentCharacter == player1 ? player2 : player1;
    }
}
