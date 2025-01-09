import classes.game.Grid;
import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        // Shape oShape = new Shape(1, new int[][]{{1, 1},{1, 1}});
        Shape lShape = new Shape(new int[][]{{2, 0},{2, 0},{2, 2}});
        Tetromino oTetromino = new Tetromino(new Position(new int[]{6, 0}), lShape);

        Grid grid = new Grid();
        grid.printGrid();
        grid.insertTetromino(oTetromino);

        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){

                grid.updateTetromino();
                grid.printGrid();
                grid.deleteTetromino();
                grid.dropTetromino();

                System.out.println();
            }
        },0,2000);


        System.out.println();
    }
}