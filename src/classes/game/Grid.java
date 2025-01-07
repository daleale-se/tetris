package classes.game;

import classes.game.tetrominoes.Tetromino;

public class Grid {

    private Cell[][] grid;
    private Tetromino mainTetromino;
    private int speed;

    public Grid() {

    }

    public void insertTetromino(Tetromino tetromino) {
        this.mainTetromino = tetromino;
    }

    public void dropTetromino() {
        this.mainTetromino.drop();
    }


}
