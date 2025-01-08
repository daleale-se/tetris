import classes.game.Grid;
import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;

public class Main {
    public static void main(String[] args) {

        // Shape oShape = new Shape(1, new int[][]{{1, 1},{1, 1}});
        Shape lShape = new Shape(new int[][]{{2, 0},{2, 0},{2, 2}});
        Tetromino oTetromino = new Tetromino(new Position(new int[]{6, 0}), "red", lShape);

        Grid grid = new Grid();
        grid.printGrid();
        grid.insertTetromino(oTetromino);

        System.out.println();

        grid.updateTetromino();
        grid.printGrid();
        grid.deleteTetromino();
        grid.dropTetromino();

        System.out.println();

        grid.updateTetromino();
        grid.printGrid();
        grid.deleteTetromino();
        grid.dropTetromino();

        System.out.println();

        grid.updateTetromino();
        grid.printGrid();
        grid.deleteTetromino();
        grid.dropTetromino();

        System.out.println();

        grid.updateTetromino();
        grid.printGrid();
        grid.deleteTetromino();
        grid.dropTetromino();

        System.out.println();

    }
}