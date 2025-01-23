package classes.game;

import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;

import java.util.ArrayList;
import java.util.Random;

public class Grid {

    private final ArrayList<ArrayList<Cell>> grid;
    private Tetromino currentTetromino;
    private final int[] size = {10, 20};

    public Grid() {
        this.grid = this.generateGrid();
        this.currentTetromino = this.randomTetronomino();
    }

    public Tetromino randomTetronomino() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Shape(new int[][]{{1, 1}, {1, 1}}));
        shapes.add(new Shape(new int[][]{{0, 0, 2}, {2, 2, 2}}));
        shapes.add(new Shape(new int[][]{{0, 3, 0}, {3, 3, 3}}));
        shapes.add(new Shape(new int[][]{{0, 4, 4}, {4, 4, 0}}));
        shapes.add(new Shape(new int[][]{{5, 5, 0}, {0, 5, 5}}));
        shapes.add(new Shape(new int[][]{{6, 6, 6}, {0, 0, 6}}));
        shapes.add(new Shape(new int[][]{{7, 7, 7, 7}}));

        int randomIndex = new Random().nextInt(shapes.size());
        return new Tetromino(new Position(new int[]{4, 0}), shapes.get(randomIndex));
    }

    public void rotateTetronomino() {
        if (this.currentTetromino.canRotate(grid)) {
            this.currentTetromino.rotateRight();
        }
    }

    private ArrayList<ArrayList<Cell>> generateGrid() {
        ArrayList<ArrayList<Cell>> aux = new ArrayList<>();
        for (int i = 0; i < size[1]; ++i) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < size[0]; ++j) {
                Cell cell = new Cell();
                row.add(cell);
            }
            aux.add(row);
        }
        return aux;
    }

    public void dropTetromino() {
        if (this.tetronominoMustStop()) {
            this.checkCompletedRows();
            this.currentTetromino = this.randomTetronomino();
        } else {
            this.currentTetromino.drop();
        }
    }

    public void updateTetromino() {
        this.currentTetromino.putOnGrid(this.grid);
    }

    public void deleteTetromino() {
        if (!this.tetronominoMustStop()) {
            this.currentTetromino.deleteFromGrid(this.grid);
        }
    }

    public void deleteTetrominoTwo() {
        this.currentTetromino.deleteFromGrid(this.grid);
    }

    public void moveTetronominoToLeft() {
        if (!this.collideByLeft()) {
            this.currentTetromino.goLeft();
        }
    }

    public void moveTetronominoToRight() {
        if (!this.collideByRight()) {
            this.currentTetromino.goRight();
        }
    }

    public boolean tetronominoMustStop() {
        return this.currentTetromino.mustStop(this.grid);
    }

    private boolean collideByLeft() {
        return this.currentTetromino.collideByLeft(this.grid);
    }

    private boolean collideByRight() {
        return this.currentTetromino.collideByRight(this.grid);
    }

    public ArrayList<ArrayList<Cell>> getGridArray() {
        return this.grid;
    }

    public void instantDrop() {
        this.currentTetromino.instantDrop(this.grid);
    }

    public boolean filledRow(ArrayList<Cell> row) {
        for (Cell cell : row) {
            if (!cell.isOccupied()) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Cell> generateEmptyRow() {
        ArrayList<Cell> row = new ArrayList<>();
        for (int j = 0; j < size[0]; ++j) {
            Cell cell = new Cell();
            row.add(cell);
        }
        return row;
    }

    public void checkCompletedRows() {
        ArrayList<Integer> deleteIndexes = new ArrayList<>();
        for (int i = grid.size() - 1; i >= 0; --i) {
            if (this.filledRow(grid.get(i))) {
                deleteIndexes.add(i);
            }
        }

        for (int index : deleteIndexes) {
            grid.remove(index);
        }

        for (int i = 0; i < deleteIndexes.size(); ++i) {
            grid.addFirst(this.generateEmptyRow());
        }
    }

}
