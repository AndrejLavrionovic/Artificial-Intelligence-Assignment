package ie.gmit.sw.ai.spiders;

import ie.gmit.sw.ai.Maze;
import ie.gmit.sw.ai.SpartanWarrior;
import ie.gmit.sw.ai.fuzzylogic.FuzzyLogic;
import ie.gmit.sw.ai.nn.NNFacade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Andrej Lavrinovic
 *
 * Created by Andrej Lavrinovic and Will Hogan on 10/04/2017.
 * Thread pool
 *
 */
public class ControllersPool {

    // Properties
    private SwarmOfSpiders spiders;
    private Maze maze;
    private SpartanWarrior warrior;

    // Service
    private ExecutorService executor = Executors.newCachedThreadPool();

    // Constructor
    // Initializes the maze and gets collection of spiders
    public ControllersPool(Maze maze, SpartanWarrior warrior){
        this.maze = maze;
        this.spiders = maze.getSpiders();
        this.warrior = warrior;
    }

    // method shares the tasks (spiders) among threads
    public void doControlling(){

        for(int i = 0; i < this.spiders.getSpidersNumber(); i++){
            executor.submit(new SpiderController(this.spiders.getSpiderByIndex(i), this.maze, new NNFacade(), new FuzzyLogic(), this.warrior));
        }
    }

}
