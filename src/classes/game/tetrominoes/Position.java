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
                    cell.changeContent(shape[shape.length - 1 - i][j]);
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

    public void drop() {
        this.y++;
    }
}
