package classes.game.tetrominoes;

import classes.game.Cell;

import java.util.ArrayList;

public class Shape {

    private int[][] shape;

    public Shape(int[][] shape) {
        this.shape = shape;
    }

    public void putOnGrid(ArrayList<ArrayList<Cell>> grid, Position position) {
        position.putOnGrid(grid, this.shape);
    }

    public void deleteFromGrid(ArrayList<ArrayList<Cell>> grid, Position position) {
        position.deleteFromGrid(grid, this.shape);
    }

    public boolean mustStop(ArrayList<ArrayList<Cell>> grid, Position position) {
        return position.mustStop(grid, this.shape);
    }

    public boolean collideByLeft(ArrayList<ArrayList<Cell>> grid, Position position) {
        return position.collideByLeft(grid, this.shape);
    }

    public boolean collideByRight(ArrayList<ArrayList<Cell>> grid, Position position) {
        return position.collideByRight(grid, this.shape);
    }

    public void rotateLeft() {

    }

}
