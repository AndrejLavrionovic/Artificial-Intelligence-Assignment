package ie.gmit.sw.ai.heuristicsearch.maze;

import ie.gmit.sw.ai.Node;

/**
 *
 * @author John Healy
 * Refactored by Andrej Lavrinovic on 21/04/2017.
 */
public interface SearchingAreaIndicator {
    Node getGoalNode();
    Node[][] getMaze();
}
