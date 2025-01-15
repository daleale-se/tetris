package classes.game.tetrominoes;

import classes.game.Cell;

import java.util.ArrayList;

public class Tetromino implements TetrominoInterface{

    private Position position;
    private Shape shape;

    public Tetromino(Position position, Shape shape) {
        this.position = position;
        this.shape = shape;
    }

    public void rotateRight() {
        this.shape.rotateRight();
    }

    @Override
    public void drop() {
        this.position.addOneToY();
    }

    @Override
    public void goLeft() {
        this.position.subtractOneToX();
    }

    @Override
    public void goRight() {
        this.position.addOneToX();
    }

    public void putOnGrid(ArrayList<ArrayList<Cell>> grid) {
        this.shape.putOnGrid(grid, this.position);
    }

    public void deleteFromGrid(ArrayList<ArrayList<Cell>> grid) {
        this.shape.deleteFromGrid(grid, this.position);
    }

    public boolean mustStop(ArrayList<ArrayList<Cell>> grid) {
        return this.shape.mustStop(grid, this.position);
    }

    public boolean collideByLeft(ArrayList<ArrayList<Cell>> grid) {
        return this.shape.collideByLeft(grid, this.position);
    }

    public boolean collideByRight(ArrayList<ArrayList<Cell>> grid) {
        return this.shape.collideByRight(grid, this.position);
    }

}
