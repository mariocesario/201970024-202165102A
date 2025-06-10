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
        while (true) {
            if (characterChoice.equalsIgnoreCase("Archer")) {
                player1 = new Archer(nickname);
                break;
            } else if (characterChoice.equalsIgnoreCase("Mage")) {
                player1 = new Mage(nickname);
                break;
            } else if (characterChoice.equalsIgnoreCase("Warrior")) {
                player1 = new Warrior(nickname);
                break;
            } else {
                System.out.println("Invalid choice! Please enter (Warrior, Mage, or Archer)");
                characterChoice = scanner.nextLine(); // Pede uma nova entrada
            }
        }

        if (isMultiplayer) {
            System.out.println("Choose Player 2's character(Warrior, Mage, or Archer):");
            String characterChoice2 = scanner.nextLine();
            System.out.println("Enter Player 2's nickname:");
            String nickname2 = scanner.nextLine();
            while (true) {
                if (characterChoice2.equalsIgnoreCase("Archer")) {
                    player2 = new Archer(nickname2);
                    break;
                } else if (characterChoice2.equalsIgnoreCase("Mage")) {
                    player2 = new Mage(nickname2);
                    break;
                } else if (characterChoice2.equalsIgnoreCase("Warrior")) {
                    player2 = new Warrior(nickname2);
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter (Warrior, Mage, or Archer)");
                    characterChoice2 = scanner.nextLine(); // Pede uma nova entrada
                }
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
            player2.setIsBot(true);
        }

        int[] position = getRandomPosition();
        int newRow = position[0];
        int newCol = position[1];
        board.placeCharacter(player1, newRow, newCol);

        position = getRandomPosition();
        newRow = position[0];
        newCol = position[1];
        board.placeCharacter(player2, newRow, newCol);

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
            validAction = isMultiplayer ? characterAction(player2) : characterBotAction(player2);
        } while (!validAction);

        board.printBoard(player1, player2);
    }

    private boolean characterBotAction(Character currentCharacter) {
        Random rand = new Random();
        int randInt = rand.nextInt(4);
        // 0 para Attack, 1 para Move, 2 para Defender, 3 para Special

        if (randInt == 0) {
            characterAttack(currentCharacter);
            System.out.println("Player 2 (bot): Attack");
        } else if (randInt == 1) {
            characterMove(currentCharacter);
            System.out.println("Player 2 (bot): Move");
        } else if (randInt == 2) {
            characterDefender(currentCharacter);
            System.out.println("Player 2 (bot): Defender");
        } else {
            characterSpecial(currentCharacter);
            System.out.println("Player 2 (bot): Special");
        }

        return true;
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
        if (currentCharacter.getIsBot()) {
            executeBotMove(currentCharacter);
        } else {
            executeDirectionalMove(currentCharacter);
        }
    }

    private void executeBotMove(Character currentCharacter) {
        int startRow = currentCharacter.getRow();
        int startCol = currentCharacter.getCol();
        int newRow = startRow;
        int newCol = startCol;

        Random rand = new Random();
        int randInt = rand.nextInt(4);
        char[] characterOptions = {'C', 'B', 'E', 'D'};
        String[] characterOptionsS = {"Up", "Down", "Left", "Right"};
        char direction = characterOptions[randInt];

        if (direction == 'C') {
            newRow -= 1;
        } else if (direction == 'B') {
            newRow += 1;
        } else if (direction == 'E') {
            newCol -= 1;
        } else {
            newCol += 1;
        }

        if (!validateMove(currentCharacter, newRow, newCol)) {
            return;
        }

        System.out.println("Bot moved " + characterOptionsS[randInt]);

        board.moveCharacter(currentCharacter, newRow, newCol);
    }


    private void executeDirectionalMove(Character currentCharacter) {
        int startRow = currentCharacter.getRow();
        int startCol = currentCharacter.getCol();
        int newRow = startRow;
        int newCol = startCol;

        System.out.println("Enter direction: C (Up), B (Down), E (Left), D (Right)");
        char direction = scanner.nextLine().toUpperCase().charAt(0);

        while (direction != 'C' && direction != 'B' && direction != 'E' && direction != 'D') {
            System.out.println("Invalid direction! Try again.");
            direction = scanner.nextLine().toUpperCase().charAt(0);
        }

        if (direction == 'C') {
            newRow -= 1;
        } else if (direction == 'B') {
            newRow += 1;
        } else if (direction == 'E') {
            newCol -= 1;
        } else {
            newCol += 1;
        }

        if (!validateMove(currentCharacter, newRow, newCol)) {
            executeDirectionalMove(currentCharacter);
            return;
        }

        board.moveCharacter(currentCharacter, newRow, newCol);
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
