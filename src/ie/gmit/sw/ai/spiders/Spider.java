package ie.gmit.sw.ai.spiders;

/**
 *
 * @author Andrej Lavrinovic
 *
 * Class is representing the spider enemy with some properties
 * and getters/setters method
 */
public class Spider {
    
    // Properties
    private int spiderNumber;
    private char spiderType;
    private int currentRow;
    private int currentCol;
    private double life = 500;
    private double health;
    private double anger;
    private double power;
    private double defence;
    
    // Constructor
    public Spider(){} // default constructor

    public Spider(int spiderNumber, char spiderType, int currentRow, int currentCol) { // parametrised constructor
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

    public double getLife() {
        return life;
    }

    public void setLife() {
        if(this.health < 300) this.life = 1;
        else if(this.health < 100) this.life = 0;
        else this.life = 2;
    }

    public double getAnger() {
        return anger;
    }

    public void setAnger(double anger) {
        this.anger = anger;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }
}
