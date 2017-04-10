package ie.gmit.sw.ai.spiders;

import ie.gmit.sw.ai.Maze;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Andrej Lavrinovic and Will Hogan on 10/04/2017.
 * @author Andrej Lavrinovic, Will Hogan
 */
public class ControllersPool {

    // Properties
    private SwarmOfSpiders spiders;
    private Maze maze;

    // Service
    private ExecutorService executor = Executors.newCachedThreadPool();

    public ControllersPool(Maze maze){
        this.maze = maze;
        this.spiders = maze.getSpiders();
    }

    public void doContolling(){

        for(int i = 0; i < this.spiders.getSpidersNumber(); i++){
            executor.submit(new SpiderController(this.spiders.getSpiderByIndex(i), this.maze));
        }
    }

}
