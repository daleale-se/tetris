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

    public void addOneToY() {
        this.y++;
    }

    public void addOneToX() {
        this.x++;
    }

    public void subtractOneToX() {
        this.x--;
    }

    public void putOnGrid(ArrayList<ArrayList<Cell>> grid, int[][] shape) {
        for (int i = 0; i < shape.length; ++i) {
            if (this.y - i >= 0) {
                for (int j = 0; j < shape[0].length; ++j) {
                    Cell cell = grid.get(y - i).get(x + j);
                    int yTarget = shape.length - 1 - i;
                    if (shape[yTarget][j] != 0) {
                        cell.changeContent(shape[yTarget][j]);
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

    public boolean mustStop(ArrayList<ArrayList<Cell>> grid, int[][] shape) {

        if (this.y + 1 >= grid.size()) {
            return true;
        }

        for (int i = 0; i < shape[0].length; ++i) {
            for (int j = 0; j < shape.length; ++j) {
                if (shape[shape.length - 1 - j][i] != 0) {
                    int nextY = this.y - j + 1;
                    int nextX = this.x + i;
                    if (nextY >= 0 && grid.get(nextY).get(nextX).isOccupied()) {
                        return true;
                    }
                    break;
                }
            }
        }

        return false;
    }

    public boolean collideByLeft(ArrayList<ArrayList<Cell>> grid, int[][] shape) {

        if (this.x - 1 < 0){
            return true;
        }

        for (int i = 0; i < shape.length; ++i) {
            for (int j = 0; j < shape[0].length; ++j) {
                int checkY = this.y - shape.length + 1 + i;
                int checkX = this.x + j;
                if (shape[i][j] != 0) {
                    if (checkY >= 0 && grid.get(checkY).get(checkX).isOccupied()) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public boolean collideByRight(ArrayList<ArrayList<Cell>> grid, int[][] shape) {

        if (this.x + shape[0].length > grid.getFirst().toArray().length - 1){
            return true;
        }

        for (int i = 0; i < shape.length; ++i) {
            for (int j = 0; j < shape[0].length; ++j) {
                int checkY = this.y - shape.length + 1 + i;
                int checkX = shape[0].length - 1 - j;
                if (shape[i][checkX] != 0) {
                    if (checkY >= 0 && grid.get(checkY).get(this.x + checkX).isOccupied()) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

}