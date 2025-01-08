package classes.game;

import classes.game.tetrominoes.Position;

public class Cell {

    private int content = 0;
    private Position position;

    Cell(Position position) {
        this.position = position;
    }

    public void changeContent(int content) {
        this.content = content;
    }

    public void printContent() {
        System.out.print(content);
    }

}
