package ie.gmit.sw.ai.heuristicsearch.traverses;

import ie.gmit.sw.ai.Node;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author John Healy
 * Refactored by Andrej Lavrinovic on 21/04/2017.
 */
public class BestFirstTraversator implements Traversator {

    private Node goal;

    public BestFirstTraversator(Node goal){
        this.goal = goal;
    }

    public void traverse(Node[][] maze, Node node) {

        LinkedList<Node> queue = new LinkedList<Node>();
        queue.addFirst(node);

        long time = System.currentTimeMillis();
        int visitCount = 0;

        while(!queue.isEmpty()){
            node = queue.poll();
            System.out.println("Node is => (" + node.getRow() + ", " + node.getCol() + ")");
            System.out.println("Goal node is => (" + goal.getRow() + ", " + goal.getCol() + ")");
            node.setVisited(true);
            visitCount++;

            if (node.isGoalNode()){
                System.out.println("goal node is found");
                //time = System.currentTimeMillis() - time; //Stop the clock
                TraversatorStats.printStats(node, time, visitCount);
                //break;
            }

            try { //Simulate processing each expanded node
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Node[] children = node.children(maze);
            for (int i = 0; i < children.length; i++) {
                if (children[i] != null && !children[i].isVisited()){
                    System.out.println("Children Num: =>" + i);
                    children[i].setParent(node);
                    queue.addFirst(children[i]);
                }
            }

            System.out.println("Childrens of this node => ");
            System.out.print("[");
            for(int i = 0; i < queue.size(); i++){
                if(i == queue.size() - 1)
                    System.out.print("(" + queue.get(i).getRow() + ", " + queue.get(i).getCol() + ")");
                else System.out.print("(" + queue.get(i).getRow() + ", " + queue.get(i).getCol() + ") -> ");
            }
            System.out.print("]\n");

            //Sort the whole queue. Effectively a priority queue, first in, best out
            Collections.sort(queue,(Node current, Node next) -> current.getHeuristic(goal) - next.getHeuristic(goal));

            System.out.println("Nodes in the sorted queue");
            System.out.print("[");
            for(int i = 0; i < queue.size(); i++){
                if(i == queue.size() - 1)
                    System.out.print("(" + queue.get(i).getRow() + ", " + queue.get(i).getCol() + ")");
                else System.out.print("(" + queue.get(i).getRow() + ", " + queue.get(i).getCol() + ") -> ");
            }
            System.out.print("]\n\n");
        }
    }
}
