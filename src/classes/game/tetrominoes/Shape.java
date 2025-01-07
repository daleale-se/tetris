package classes.game.tetrominoes;

public class Shape {

    private int[][] shape;
    private char name;

    public Shape(char name, int[][] shape) {
        this.name = name;
        this.shape = shape;
    }
}
