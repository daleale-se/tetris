package classes.game;

public class Cell {

    private int content = 0;
    /* private Position position;

    Cell(Position position) {
        this.position = position;
    } */

    public void changeContent(int content) {
        this.content = content;
    }

    public void printContent() {
        System.out.print(content);
    }

    public boolean isOccupied() {
        return content != 0;
    }

}