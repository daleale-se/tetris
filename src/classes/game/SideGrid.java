package classes.game;

import java.util.ArrayList;

public class SideGrid {

    private final ArrayList<ArrayList<Cell>> grid;
    private final int[] size = {6, 8};


    public SideGrid() {
        grid = this.generateGrid();
    }

    private ArrayList<ArrayList<Cell>> generateGrid() {
        ArrayList<ArrayList<Cell>> aux = new ArrayList<>();
        for (int i = 0; i < size[1]; ++i) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < size[0]; ++j) {
                Cell cell = new Cell();
                row.add(cell);
            }
            aux.add(row);
        }
        return aux;
    }


}
