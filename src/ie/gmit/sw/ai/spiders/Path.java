package ie.gmit.sw.ai.spiders;


import ie.gmit.sw.ai.Node;

import java.util.LinkedList;

/**
 *
 * @author Andrej Lavrinovic g00196984
 * Created by Andrej Lavrinovic on 21/04/2017.
 *
 * Path is the FIFO queue of Nodes linked between each other
 * and represents the shortest available path to the goal Node.
 * The last Node in the queue is the goal node,
 * the first Node in the is the next available node to step in.
 */
public class Path {

    private LinkedList<Node> path = new LinkedList< Node>();

    public void insertNode(Node node) {
        path.addLast(node);
    }

    public int pathSize() {
        return path.size();
    }

    public void clearPath() {
        path.clear();
    }

    public Node takeLastNodeOf() {
        return path.pollLast();
    }

    public Node getLastNode(){
        return path.getLast();
    }
}
