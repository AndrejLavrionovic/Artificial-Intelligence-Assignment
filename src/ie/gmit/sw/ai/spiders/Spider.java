/*
 * Class is representing the spider enemy with some properties
 * and getters/setters method
 */
package ie.gmit.sw.ai.spiders;

/**
 *
 * @author Andrej Lavrinovic, Will Hogan
 */
public class Spider {
    
    // Properties
    private int spiderNumber;
    private char spiderType;
    private int currentRow;
    private int currentCol;
    
    // Constructor
    public Spider(){}

    public Spider(int spiderNumber, char spiderType, int currentRow, int currentCol) {
        this.spiderNumber = spiderNumber;
        this.spiderType = spiderType;
        this.currentRow = currentRow;
        this.currentCol = currentCol;
    }

    // Getters/Setters
    public int getSpiderNumber() {
        return spiderNumber;
    }

    public void setSpiderNumber(int spiderNumber) {
        this.spiderNumber = spiderNumber;
    }

    public char getSpiderType() {
        return spiderType;
    }

    public void setSpiderType(char spiderType) {
        this.spiderType = spiderType;
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
