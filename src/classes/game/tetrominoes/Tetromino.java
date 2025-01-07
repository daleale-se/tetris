package classes.game.tetrominoes;

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

    }

    @Override
    public void goLeft() {

    }

    @Override
    public void goRight() {

    }
}
