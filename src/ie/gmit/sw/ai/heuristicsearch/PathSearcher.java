package ie.gmit.sw.ai.heuristicsearch;

import ie.gmit.sw.ai.Maze;
import ie.gmit.sw.ai.Node;
import ie.gmit.sw.ai.heuristicsearch.maze.IndicateArea;
import ie.gmit.sw.ai.heuristicsearch.maze.SearchingAreaIndicator;
import ie.gmit.sw.ai.heuristicsearch.traverses.BestFirstTraversator;
import ie.gmit.sw.ai.heuristicsearch.traverses.Traversator;

import java.util.LinkedList;

/**
 *
 * @author Andrej Lavrinovic g00196984
 * Created by Andrej Lavrinovic on 21/04/2017.
 */
public class PathSearcher {

    private Maze superMaze;

    public PathSearcher(Maze superMaze){
        this.superMaze = superMaze;
    }

    // this method must be called for searching the shortest path from the spider to the warrior.
    public LinkedList<Node> search(Node initialNode, Node goalNode){
        // 1) indicate searching area
        SearchingAreaIndicator area = new IndicateArea(getAreaHeight(initialNode, goalNode), getAreaWidth(initialNode, goalNode),
                getLeftTopCorner(initialNode, goalNode), this.superMaze, goalNode, initialNode);

        // 2) traverse the maze
        Traversator t = new BestFirstTraversator(goalNode);

        t.traverse(area.getMaze(), initialNode);

        return t.getStack();
    }

    private int getAreaHeight(Node initialNode, Node goalNode){
        int height;
        if(initialNode.getRow() > goalNode.getRow())
            height = initialNode.getRow() - goalNode.getRow() + 1;
        else height = goalNode.getRow() - initialNode.getRow() + 1;
        return height;
    }

    private int getAreaWidth(Node initialNode, Node goalNode){
        int width;
        if(initialNode.getCol() > goalNode.getCol())
            width = initialNode.getCol() - goalNode.getCol() + 1;
        else width = goalNode.getCol() - initialNode.getCol() + 1;
        return width;
    }

    private Node getLeftTopCorner(Node initialNode, Node goalNode){
        int row, col;
        if(initialNode.getRow() < goalNode.getRow())
            row  = initialNode.getRow();
        else row = goalNode.getRow();
        if(initialNode.getCol() < goalNode.getCol())
            col = initialNode.getCol();
        else col = goalNode.getCol();

        return new Node(row, col);
    }
}
