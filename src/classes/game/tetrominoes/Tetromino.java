package classes.game.tetrominoes;

import classes.game.Cell;

import java.util.ArrayList;

public class Tetromino implements TetrominoInterface{

    private Position position;
    private String color;
    private Shape shape;

    public Tetromino(Position position, String color, Shape shape) {
        this.position = position;
        this.color = color;
        this.shape = shape;
    }

    @Override
    public void rotateLeft() {

    }

    @Override
    public void drop() {
        this.position.drop();
    }

    @Override
    public void goLeft() {

    }

    @Override
    public void goRight() {

    }

    public void putOnGrid(ArrayList<ArrayList<Cell>> grid) {
        this.shape.putOnGrid(grid, this.position);
    }

    public void deleteFromGrid(ArrayList<ArrayList<Cell>> grid) {
        this.shape.deleteFromGrid(grid, this.position);
    }

}
