package classes.game;

import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Tetromino;

import java.util.ArrayList;

public class Grid {

    private final ArrayList<ArrayList<Cell>> grid;
    private Tetromino currentTetromino;
    private int speed;
    private final int[] size = {14, 16};

    public Grid() {
        this.grid = this.generateGrid();
    }

    private ArrayList<ArrayList<Cell>> generateGrid() {
        ArrayList<ArrayList<Cell>> aux = new ArrayList<>();
        for (int i = 0; i < size[1]; ++i) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < size[0]; ++j) {
                Cell cell = new Cell(new Position(new int[]{j, i}));
                row.add(cell);
            }
            aux.add(row);
        }
        return aux;
    }

    public void insertTetromino(Tetromino tetromino) {
        this.currentTetromino = tetromino;
    }

    public void dropTetromino() {
        this.currentTetromino.drop();
    }

    public void updateTetromino() {
        this.currentTetromino.putOnGrid(this.grid);
    }

    public void deleteTetromino() {
        this.currentTetromino.deleteFromGrid(this.grid);
    }

    public void printGrid() {
        for (ArrayList<Cell> row : this.grid) {
            for (Cell cell : row) {
                cell.printContent();
            }
            System.out.println();
        }
    }

}
