package ie.gmit.sw.ai.spiders;

import ie.gmit.sw.ai.Maze;
import ie.gmit.sw.ai.Movable;
import ie.gmit.sw.ai.nn.GameRunner;

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
    private GameRunner gr;

    // Constructor that initializes instances
    public SpiderController(Spider spider, Maze maze, GameRunner gr){
        this.maze = maze;
        this.spider = spider;
        this.gr = gr;
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


        if(row <= this.maze.size() - 1 && col <= this.maze.size() -1
                && this.maze.get(row, col) == ' '){ // if move is valid
            maze.set(spider.getCurrentRow(), spider.getCurrentCol(), '\u0020'); // replace current cell with space

            // set new coordinates for object
            spider.setCurrentRow(row);
            spider.setCurrentCol(col);
            maze.set(row, col, spider.getSpiderType()); // place object to the destination cell
        }
        else if(row <= this.maze.size() - 1 && col <= this.maze.size() -1
                && this.maze.get(row, col) == '5'){
            try {
                gr.action(spider.getLife(), spider.getAnger(), spider.getPower(), spider.getDefence());
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

        while(true){ // infinite run
            
            try {
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
                Thread.sleep(1000); // Sleep for 1 sec.
            } catch (InterruptedException ex) {
                Logger.getLogger(SpiderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
