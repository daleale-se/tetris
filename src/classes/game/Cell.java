package classes.game;

public class Cell {

    private int content = 0;

    public void changeContent(int content) {
        this.content = content;
    }

    public void printContent() {
        System.out.print(content);
    }

    public boolean isOccupied() {
        return content != 0;
    }

    public String getContent() {
        return Integer.toString(this.content);
    }

}
