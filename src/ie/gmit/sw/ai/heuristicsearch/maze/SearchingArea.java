package ie.gmit.sw.ai.heuristicsearch.maze;

import ie.gmit.sw.ai.Node;

/**
 *
 * @author John Healy
 * Refactored by Andrej Lavrinovic (g00196984) on 21/04/2017.
 */
public class SearchingArea {

    // Properties
    private Node[][] maze;
    private int currentRow;
    private int currentCol;

    public SearchingArea(Node[][] maze) {
        this.maze = maze;
    }

    //
    public void setCurrentPosition(int row, int col){
        this.currentRow = row;
        this.currentCol = col;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }
}
