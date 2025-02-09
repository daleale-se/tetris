package classes.game;

public class Cell {

    private int content = 0;

    public void changeContent(int content) {
        this.content = content;
    }

    public boolean isOccupied() {
        return content != 0;
    }

    public int getContent() {
        return this.content;
    }

}
