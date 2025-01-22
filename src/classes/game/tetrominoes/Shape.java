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

    public void rotateRight() {
        this.shape = this.getRotatedShape();
    }

    public int[][] getRotatedShape() {
        int width = this.shape[0].length;
        int height = this.shape.length;
        int[][] rotatedShape = new int[width][height];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                rotatedShape[j][height - 1 - i] = this.shape[i][j];
            }
        }
        return rotatedShape;
    }

    public boolean canRotate(ArrayList<ArrayList<Cell>> grid, Position position) {
        int[][] rotatedShape = this.getRotatedShape();
        return position.canRotate(grid, rotatedShape);
    }

}
