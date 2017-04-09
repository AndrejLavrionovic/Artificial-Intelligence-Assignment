/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.gmit.sw.ai;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrej Lavrinovic, Will Hogan
 */
public class SpidersControll implements Movable{
    
    private SpidersArmy spiders;
    private Spider spider;
    private Maze maze;
    
    public SpidersControll(Maze maze){
        this.maze = maze;
        this.spiders = this.maze.getSpiders();
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
        
        spider = this.spiders.getSpiderByIndex(100);
        while(true){
            
            try {
                int derection = (int) (4 * Math.random() + 1);
                switch(derection){
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
                System.out.println("==> Spider coordinates == (" + spider.getCurrentRow() + " : " + spider.getCurrentCol() + ")");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SpidersControll.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
