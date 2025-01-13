/*
import classes.game.Grid;

import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;

import java.awt.event.KeyEvent;
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
        },0,1500);

        System.out.println();
    }

    public void keyTyped(KeyEvent event, Grid grid) {
        if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            // goDown
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            grid.deleteTetromino();
            grid.moveTetronominoToLeft();
            grid.updateTetromino();
            grid.printGrid();
        }
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            grid.deleteTetromino();
            grid.moveTetronominoToRight();
            grid.updateTetromino();
            grid.printGrid();
        }
    }

}
*/


import classes.game.Grid;
import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame implements KeyListener {
    private Grid grid;
    private Tetromino currentTetromino;

    public Main() {
        // Initialize the grid and Tetromino
        grid = new Grid();
        Shape lShape = new Shape(new int[][]{{2, 0}, {2, 0}, {2, 2}});
        currentTetromino = new Tetromino(new Position(new int[]{6, 0}), lShape);
        grid.insertTetromino(currentTetromino);

        // Print the initial grid
        grid.printGrid();

        // Add KeyListener to the JFrame
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        // Timer to handle automatic dropping of the Tetromino
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                grid.updateTetromino();
                grid.printGrid();
                grid.dropTetromino();
                System.out.println();
            }
        }, 0, 1500);

        // Set up JFrame
        this.setTitle("Tetris Game");
        this.setSize(400, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                grid.deleteTetromino();
                grid.dropTetromino();
                grid.updateTetromino();
                grid.printGrid();
                break;
            case KeyEvent.VK_LEFT:
                grid.deleteTetromino();
                grid.moveTetronominoToLeft();
                grid.updateTetromino();
                grid.printGrid();
                break;
            case KeyEvent.VK_RIGHT:
                grid.deleteTetromino();
                grid.moveTetronominoToRight();
                grid.updateTetromino();
                grid.printGrid();
                break;
            case KeyEvent.VK_UP:
                grid.deleteTetromino();
                // Add a method to rotate the Tetromino
                // grid.rotateTetromino();
                grid.updateTetromino();
                grid.printGrid();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        // Not used but required by the interface
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // Not used but required by the interface
    }
}
