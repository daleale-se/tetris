import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;

public class Main {
    public static void main(String[] args) {



        Shape oShape = new Shape('O', new int[][]{{1,1},{1,1}});
        Tetromino oTetromino = new Tetromino(new Position(), "red", oShape);

    }
}