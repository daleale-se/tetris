package classes.game.tetrominoes;

import classes.game.Cell;

import java.util.ArrayList;

public class Position {

    private int x;
    private int y;

    public Position(int[] pos) {
        this.x = pos[0];
        this.y = pos[1];
    }

    public void putOnGrid(ArrayList<ArrayList<Cell>> grid, int[][] shape) {
        for (int i = 0; i < shape.length; ++i) {
            if (this.y - i >= 0) {
                for (int j = 0; j < shape[0].length; ++j) {
                    Cell cell = grid.get(y - i).get(x + j);
                    if (shape[shape.length - 1 - i][j] != 0) {
                        cell.changeContent(shape[shape.length - 1 - i][j]);
                    }
                }
            }
        }
    }

    public void deleteFromGrid(ArrayList<ArrayList<Cell>> grid, int[][] shape) {
        for (int i = 0; i < shape.length; ++i) {
            if (this.y - i >= 0) {
                for (int j = 0; j < shape[0].length; ++j) {
                    Cell cell = grid.get(y - i).get(x + j);
                    cell.changeContent(0);
                }
            }
        }
    }

    public void addOneToY() {
        this.y++;
    }

    public void addOneToX() {
        this.x++;
    }

    public void subtractOneToX() {
        this.x--;
    }

    public boolean mustStop(ArrayList<ArrayList<Cell>> grid, int[][] shape) {

        if (this.y + 1 >= grid.toArray().length) {
            return true;
        }

        for (int i = 0; i < shape[0].length; ++i) {
            for (int j = 0; j < shape.length; ++j) {
                if (shape[shape.length - 1 - j][i] != 0) {
                    if (this.y - j + 1 >= 0 && grid.get(this.y - j + 1).get(this.x + i).isOccupied()) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

}
