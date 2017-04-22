package ie.gmit.sw.ai.spiders;

import ie.gmit.sw.ai.Maze;
import ie.gmit.sw.ai.Movable;
import ie.gmit.sw.ai.Node;
import ie.gmit.sw.ai.SpartanWarrior;
import ie.gmit.sw.ai.fuzzylogic.FuzzyLogic;
import ie.gmit.sw.ai.heuristicsearch.PathSearcher;
import ie.gmit.sw.ai.nn.NNFacade;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrej Lavrinovic
 *
 * SpiderController.java implements Movable interface and make spiders moving.
 * This class also implement Runnable to have separate processor to control each spider.
 */
public class SpiderController implements Movable, Runnable {

    // Instances
    private Spider spider;
    private Maze maze;
    private NNFacade gr;
    private FuzzyLogic fl;
    private SpartanWarrior warrior;

    // Constructor that initializes instances
    public SpiderController(Spider spider, Maze maze, NNFacade gr, FuzzyLogic fl, SpartanWarrior warrior){
        this.maze = maze;
        this.spider = spider;
        this.gr = gr;
        this.fl = fl;
        this.warrior = warrior;
    }

    // Run method that implemented from Runnable
    @Override
    public void run(){
        moveSpider();
    }

    /*
    * move() method implemented from Movable
    * It takes coordinates of the destination cell and movable object (Spider)
    * In the body the method checks for availability to move to the destination
    * and if there is valid move performs that move
    *
     */
    @Override
    public void move(int row, int col, Spider spider) {

        // cell where currently the spider is
        int[] current = {spider.getCurrentRow(), spider.getCurrentCol()};

        if(isValidMove(row, col)){ // if move is valid
            doStep(current, row, col, spider);
        }
        // if spider will meet the warrior
        else if(row <= this.maze.size() - 1 && col <= this.maze.size() -1
                && this.maze.get(row, col) == '5'){
            try {

                // get result from NN
                int nnOutput = gr.action(spider.getLife(), spider.getAnger(), spider.getPower(), spider.getDefence());
                switch(nnOutput){
                    case 1: // walk away - just step to the next valid sell
                        if(isValidMove(spider.getCurrentRow(), (spider.getCurrentCol() + 1))){
                            doStep(current, spider.getCurrentRow(), (spider.getCurrentCol() + 1), spider);
                        }
                        else if(isValidMove(spider.getCurrentRow(), (spider.getCurrentCol() - 1))){
                            doStep(current, spider.getCurrentRow(), (spider.getCurrentCol() - 1), spider);
                        }
                        else if(isValidMove((spider.getCurrentRow() + 1), spider.getCurrentCol())){
                            doStep(current, (spider.getCurrentRow() + 1), spider.getCurrentCol(), spider);
                        }
                        else if(isValidMove((spider.getCurrentRow() - 1), spider.getCurrentCol())){
                            doStep(current, (spider.getCurrentRow() - 1), spider.getCurrentCol(), spider);
                        }
                        else{
                            // attack the warrior
                            System.out.println("Attack the warrior");
                            attack(spider);
                        }
                        break;
                    case 2: // attack
                        // attack the warrior
                        System.out.println("Attack the warrior");
                        attack(spider);
                        break;
                    case 3: // Build hedge - step to the next valid sell and replace the current cell with the hedge
                        if(isValidMove(spider.getCurrentRow(), (spider.getCurrentCol() + 1))){
                            buildHedge(current, spider.getCurrentRow(), (spider.getCurrentCol() + 1), spider);
                        }
                        else if(isValidMove(spider.getCurrentRow(), (spider.getCurrentCol() - 1))){
                            buildHedge(current, spider.getCurrentRow(), (spider.getCurrentCol() - 1), spider);
                        }
                        else if(isValidMove((spider.getCurrentRow() + 1), spider.getCurrentCol())){
                            buildHedge(current, (spider.getCurrentRow() + 1), spider.getCurrentCol(), spider);
                        }
                        else if(isValidMove((spider.getCurrentRow() - 1), spider.getCurrentCol())){
                            buildHedge(current, (spider.getCurrentRow() - 1), spider.getCurrentCol(), spider);
                        }
                        else{
                            // attack the warrior
                            System.out.println("Attack the warrior");
                            attack(spider);
                        }
                        break;
                    case 4: // Run away - just jump out for 3 cells
                        if(isValidMove(spider.getCurrentRow(), (spider.getCurrentCol() + 3))){
                            doStep(current, spider.getCurrentRow(), (spider.getCurrentCol() + 3), spider);
                        }
                        else if(isValidMove(spider.getCurrentRow(), (spider.getCurrentCol() - 3))){
                            doStep(current, spider.getCurrentRow(), (spider.getCurrentCol() - 3), spider);
                        }
                        else if(isValidMove((spider.getCurrentRow() + 3), spider.getCurrentCol())){
                            doStep(current, (spider.getCurrentRow() + 3), spider.getCurrentCol(), spider);
                        }
                        else if(isValidMove((spider.getCurrentRow() - 3), spider.getCurrentCol())){
                            doStep(current, (spider.getCurrentRow() - 3), spider.getCurrentCol(), spider);
                        }
                        else{
                            // attack the warrior
                            System.out.println("Attack the warrior");
                            attack(spider);
                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    *
    * Method randomly generates one of the four values:
    * 1 - North
    * 2 - East
    * 3 - South
    * 4 - West
    * and moves the object to the decided cell
    * then sleeps for the second and performs again infinitely.
    *
     */
    public void moveSpider(){
        int x1, x2, y1, y2;
        while(spider.getHealth() > 0){ // infinite run

            x1 = this.spider.getCurrentRow();
            y1 = this.spider.getCurrentCol();
            x2 = this.warrior.getCurrentRow();
            y2 = this.warrior.getCurrentCol();

            try {
                if(isHuntingDistance(x1, y1, x2, y2, this.spider.getSence())){
                    Node spiderNode = new Node(x1, y1);
                    Node warriorNode = new Node(x2, y2);
                    PathSearcher ps = new PathSearcher(this.maze);
                    ps.search(spiderNode, warriorNode);
                    //System.out.println("Spider is in hunting area");
                }

                int direction = (int) (4 * Math.random() + 1); // generates number 1 - 4 inclusive
                switch(direction){
                    case 1:
                        move(spider.getCurrentRow() - 1, spider.getCurrentCol(), spider); // Move North
                        break;
                    case 2:
                        move(spider.getCurrentRow(), spider.getCurrentCol() + 1, spider); // Move East
                        break;
                    case 3:
                        move(spider.getCurrentRow() + 1, spider.getCurrentCol(), spider); // Move South
                        break;
                    case 4:
                        move(spider.getCurrentRow(), spider.getCurrentCol() - 1, spider); // Move West
                        break;
                }
                // System.out.println("==> Spider coordinates == (" + spider.getCurrentRow() + " : " + spider.getCurrentCol() + ")");
                Thread.sleep(5000); // Sleep for 1 sec.
            } catch (InterruptedException ex) {
                Logger.getLogger(SpiderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isValidMove(int row, int col){
        if(row <= this.maze.size() - 1 && col <= this.maze.size() -1
                && this.maze.get(row, col) == ' ') return true;
        return false;
    }

    public void doStep(int[] current, int destinationRow, int destinationCol, Spider spider){
        this.maze.set(current[0], current[1], '\u0020'); // replace current cell with space

        // set new coordinates for object
        spider.setCurrentRow(destinationRow);
        spider.setCurrentCol(destinationCol);
        this.maze.set(destinationRow, destinationCol, spider.getSpiderType()); // place object to the destination cell
    }

    public void buildHedge(int[] current, int destinationRow, int destinationCol, Spider spider){
        this.maze.set(current[0], current[1], '0'); // replace current cell with space

        // set new coordinates for object
        spider.setCurrentRow(destinationRow);
        spider.setCurrentCol(destinationCol);
        this.maze.set(destinationRow, destinationCol, spider.getSpiderType()); // place object to the destination cell
    }

    public void attack(Spider spider){

        // using fuzzylogic estimating the warrior life after battle
        this.warrior.setDamageTaken(this.fl.engage(this.warrior, spider, this.warrior.useWeapon()));
        System.out.println("Spartan LifeForce After battle is: " + this.warrior.getLifeForce());

        spider.setHealth(spider.getHealth() - 250); // assuming that spider lost its health
        spider.setLife(); // setting the life (0 - 2)
        if(spider.getHealth() <= 0){ // if spider is killed, then remove it from the list
            this.maze.set(spider.getCurrentRow(), spider.getCurrentCol(), ' ');
            this.maze.getSpiders().removeSpider(spider);
            spider = null;
            System.out.println("Spiders left => " + this.maze.getSpiders().getSpidersNumber());
        }
    }

    private boolean isHuntingDistance(int x1, int y1, int x2, int y2, int spiderSence){
        if((((x1 - x2) <= spiderSence) && ((x1 - x2) >= (-1 * spiderSence))) &&
                (((y1 - y2) <= spiderSence) && ((y1 - y2) >= (-1 * spiderSence)))){
            return true;
        }
        return false;
    }
}
