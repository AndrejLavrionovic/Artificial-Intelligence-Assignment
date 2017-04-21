package ie.gmit.sw.ai.heuristicsearch.traverses;

import ie.gmit.sw.ai.Node;

/**
 * @author John Healy
 * Refactored by Andrej Lavrinovic on 21/04/2017.
 */
public class TraversatorStats {
    public static void printStats(Node node, long time, int visitCount){
        double depth = 0;

        int count = 0;
        while (node != null){
            count++;
            System.out.println("Node (" + count + ") ===> (" + node.getRow() + ", " + node.getCol() + ")");
            node = node.getParent();
            depth++;
        }

        System.out.println("Visited " + visitCount + " nodes in " + time + "ms.");
        System.out.println("Found goal at a depth of " + String.format("%.0f", depth));
        System.out.println("EBF = B* = k^(1/d) = " + String.format("%.2f", Math.pow((double) visitCount, (1.00d / depth))));
    }
}
