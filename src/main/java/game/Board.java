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

    public Character getCharacter(int row, int col) {
        return grid[row][col];
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

    public int calculateDistance(Character c1, Character c2) {
        int dRow = Math.abs(c1.getRow() - c2.getRow());
        int dCol = Math.abs(c1.getCol() - c2.getCol());
        return Math.max(dRow, dCol);
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print((grid[i][j] == null ? "." : grid[i][j].getName().charAt(0)) + " ");
            }
            System.out.println();
        }
    }
}
