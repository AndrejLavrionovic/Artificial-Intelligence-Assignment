package ie.gmit.sw.ai.heuristicsearch.traverses;

import ie.gmit.sw.ai.Node;

/**
 * @author John Healy
 * Refactored by Andrej Lavrinovic on 21/04/2017.
 */
public interface Traversator {
    void traverse(Node[][] maze, Node start);
}
