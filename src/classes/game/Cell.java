package classes.game;

public class Cell {
    private boolean isEmpty = true;
    private int[] position;

    Cell(int[] position) {
        this.position = position;
    }

    public void changeState() {
        this.isEmpty = !this.isEmpty;
    }
}
