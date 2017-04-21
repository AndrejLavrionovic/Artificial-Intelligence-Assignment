package ie.gmit.sw.ai.heuristicsearch.maze;

import ie.gmit.sw.ai.Maze;
import ie.gmit.sw.ai.Node;
import ie.gmit.sw.ai.Node.Direction;
/**
 *
 * @author John Healy
 * Refactored by Andrej Lavrinovic on 21/04/2017.
 */
public class IndicateArea implements SearchingAreaIndicator {

    private Node[][] maze;
    private Node goalNode;
    private Node initialNode;
    private Node startNode;
    private Maze superMaze;


    // Constructor
    public IndicateArea(int rows, int cols, Node startNode, Maze superMaze, Node goalNode, Node initialNode) {
        maze = new Node[rows][cols];
        this.startNode = startNode;
        this.superMaze = superMaze;
        setGoalNode(goalNode);
        setInitialNode(initialNode);
        init();
    }

    // getters/setters
    public void setGoalNode(Node goalNode) {
       this.goalNode = goalNode;
    }
    public void setInitialNode(Node initialNode) {this.initialNode = initialNode;}

    public Node getGoalNode() {
        return goalNode;
    }

    public Node[][] getMaze() {
        return this.maze;
    }

    // creating the searching area
    protected void init() {

        int startRow = this.startNode.getRow();
        int height = this.startNode.getRow() + maze.length;
        int startCol = this.startNode.getCol();
        int length = this.startNode.getCol() + maze[0].length;

        for (int row = startRow, r = 0; row < height; row++, r++){
            for (int col = startCol, c = 0; col < length; col++, c++){
                Node cell = new Node(row, col, r, c);

                // other cell settings going here....
                cell.setVisited(false);
                cell.setParent(null);
                cell.setNodeType(this.superMaze.get(row, col));
                if(cell.getNodeType() == ' '){
                    cell.setWalkable(true);
                }
                if(cell.getRow() == this.goalNode.getRow() && cell.getCol() == this.goalNode.getCol()){
                    cell.setGoalNode(true);
                }
                if(cell.getRow() == this.initialNode.getRow() && cell.getCol() == this.initialNode.getCol()){
                    cell.setInitial(true);
                }

                //...................................

                this.maze[r][c] = cell;
            }
        }
    }

    protected Direction getDirection(Node current, Node adjacent){
        if (adjacent.getRow() == current.getRow() - 1 && adjacent.getCol() == current.getCol() && adjacent.isWalkable()) return Direction.North;
        if (adjacent.getRow() == current.getRow() + 1 && adjacent.getCol() == current.getCol() && adjacent.isWalkable()) return Direction.South;
        if (adjacent.getRow() == current.getRow() && adjacent.getCol() == current.getCol() - 1 && adjacent.isWalkable()) return Direction.West;
        if (adjacent.getRow() == current.getRow() && adjacent.getCol() == current.getCol() + 1 && adjacent.isWalkable()) return Direction.East;
        return null;
    }


    protected Direction opposite(Direction dir){
        if (dir == Direction.North) return Direction.South;
        if (dir == Direction.South) return Direction.North;
        if (dir == Direction.East) return Direction.West;
        if (dir == Direction.West) return Direction.East;
        return null;
    }



    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int row = 0; row < maze.length; row++){
            for (int col = 0; col < maze[row].length; col++){
                Node.Direction[] dirs = maze[row][col].getPaths();
                sb.append("(");
                for (int i = 0; i < dirs.length; i++){
                    sb.append(dirs[i]);
                }
                sb.append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
