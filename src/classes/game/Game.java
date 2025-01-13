package classes.game;

import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    public Tetromino randomTetronomino() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Shape(new int[][]{{1, 1}, {1, 1}}));
        shapes.add(new Shape(new int[][]{{2, 0}, {2, 0}, {2, 2}}));
        shapes.add(new Shape(new int[][]{{3, 0}, {3, 3}, {3, 0}}));
        shapes.add(new Shape(new int[][]{{4, 0}, {4, 4}, {0, 4}}));
        shapes.add(new Shape(new int[][]{{0, 5}, {5, 5}, {5, 0}}));
        shapes.add(new Shape(new int[][]{{0, 6}, {0, 6}, {6, 6}}));
        shapes.add(new Shape(new int[][]{{7, 0}, {7, 0}, {7, 0},{7, 0}}));

        int randomIndex = new Random().nextInt(shapes.size());
        return new Tetromino(new Position(new int[]{6, 0}), shapes.get(randomIndex));
    }

}
