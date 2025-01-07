package classes.game.tetrominoes;

public class Position {

    private int x;
    private int y;

    public Position() {
        this.x = 4;
        this.y = 0;
    }

    public void setPos(int[] pos) {
        this.x = pos[0];
        this.y = pos[1];
    }
}
