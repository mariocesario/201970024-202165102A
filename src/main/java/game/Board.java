package game;

import character.Character;

public class Board {
    private final int rows = 10;
    private final int columns = 10;
    private final Character[][] grid;

    public Board() {
        grid = new Character[rows][columns];
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    public boolean isOccupied(int row, int col) {
        return grid[row][col] != null;
    }

    public void placeCharacter(Character c, int row, int col) {
        if (!isValidPosition(row, col) || isOccupied(row, col)) {
            throw new IllegalArgumentException("Invalid or occupied position");
        }
        c.setPosition(row, col);
        grid[row][col] = c;
    }

    public void moveCharacter(Character c, int newRow, int newCol) {
        if (!isValidPosition(newRow, newCol) || isOccupied(newRow, newCol)) {
            throw new IllegalArgumentException("Invalid move");
        }
        grid[c.getRow()][c.getCol()] = null;
        c.setPosition(newRow, newCol);
        grid[newRow][newCol] = c;
    }


    // Método para converter índice numérico em letra
    public String columnToLetter(int col) {
        return String.valueOf((char) ('A' + col));
    }

    public void printBoard(Character c1, Character c2) {
        System.out.println();

        c1.printData();
        c2.printData();

        System.out.print("  ");
        for (int j = 0; j < columns; j++) {
            System.out.print(columnToLetter(j) + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < columns; j++) {
                System.out.print((grid[i][j] == null ? "." : grid[i][j].getName().charAt(0)) + " ");
            }
            System.out.println();
        }
    }
}
