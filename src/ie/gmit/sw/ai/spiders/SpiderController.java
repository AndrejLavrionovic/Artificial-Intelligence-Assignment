/*
 * SpiderController.java implements Movable interface and make spiders moving.
 * This class also implement Runnable to have separate processor to control each spider.
 */
package ie.gmit.sw.ai.spiders;

import ie.gmit.sw.ai.Maze;
import ie.gmit.sw.ai.Movable;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrej Lavrinovic, Will Hogan
 */
public class SpiderController implements Movable, Runnable {

    private Spider spider;
    private Maze maze;
    
    public SpiderController(Spider spider, Maze maze){
        this.maze = maze;
        this.spider = spider;
    }

    @Override
    public void run(){
        moveSpider();
    }

    @Override
    public void move(int row, int col, Spider spider) {
        if(row <= this.maze.size() - 1 && col <= this.maze.size() -1
                && this.maze.get(row, col) == ' '){
            maze.set(spider.getCurrentRow(), spider.getCurrentCol(), '\u0020');
            spider.setCurrentRow(row);
            spider.setCurrentCol(col);
            maze.set(row, col, spider.getSpiderType());
        }
    }

    public void moveSpider(){

        while(true){
            
            try {
                int direction = (int) (4 * Math.random() + 1);
                switch(direction){
                    case 1:
                        move(spider.getCurrentRow() - 1, spider.getCurrentCol(), spider);
                        break;
                    case 2:
                        move(spider.getCurrentRow(), spider.getCurrentCol() + 1, spider);
                        break;
                    case 3:
                        move(spider.getCurrentRow() + 1, spider.getCurrentCol(), spider);
                        break;
                    case 4:
                        move(spider.getCurrentRow(), spider.getCurrentCol() - 1, spider);
                        break;
                }
                // System.out.println("==> Spider coordinates == (" + spider.getCurrentRow() + " : " + spider.getCurrentCol() + ")");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SpiderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
