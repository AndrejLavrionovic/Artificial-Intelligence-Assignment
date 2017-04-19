package ie.gmit.sw.ai;


import ie.gmit.sw.ai.spiders.Spider;
import ie.gmit.sw.ai.spiders.SwarmOfSpiders;

public class Maze {
    
    // Property
    // Representation of maze in two dementional array;
    private char[][] maze;
    private SwarmOfSpiders spiders;

    // Constructor
    public Maze(int dimension){
        maze = new char[dimension][dimension];
        init();
        buildMaze();
        this.spiders = new SwarmOfSpiders();

        int featureNumber = (int)((dimension * dimension) * 0.01);
        addFeature('\u0031', '0', featureNumber); //1 is a sword, 0 is a hedge
        addFeature('\u0032', '0', featureNumber); //2 is help, 0 is a hedge
        addFeature('\u0033', '0', featureNumber); //3 is a bomb, 0 is a hedge
        addFeature('\u0034', '0', featureNumber); //4 is a hydrogen bomb, 0 is a hedge

        featureNumber = (int)((dimension * dimension) * 0.01);
        addFeature('\u0036', '0', featureNumber); //6 is a Black Spider, 0 is a hedge
        addFeature('\u0037', '0', featureNumber); //7 is a Blue Spider, 0 is a hedge
        addFeature('\u0038', '0', featureNumber); //8 is a Brown Spider, 0 is a hedge
        addFeature('\u0039', '0', featureNumber); //9 is a Green Spider, 0 is a hedge
        addFeature('\u003A', '0', featureNumber); //: is a Grey Spider, 0 is a hedge
        addFeature('\u003B', '0', featureNumber); //; is a Orange Spider, 0 is a hedge
        addFeature('\u003C', '0', featureNumber); //< is a Red Spider, 0 is a hedge
        addFeature('\u003D', '0', featureNumber); //= is a Yellow Spider, 0 is a hedge
        // addFeature('\u003F', '0', featureNumber); //? is the Spartan Warriors Exit, 0 is a hedge
    }

    // Builds the maze, initialy with hedges with no space, spiders and items.
    private void init(){
        for (int row = 0; row < maze.length; row++){
            for (int col = 0; col < maze[row].length; col++){
                maze[row][col] = '0'; //Index 0 is a hedge...
            }
        }
    }


    /* 
    * This method replaces hedges with features
    * It is randomly picks the cell and if this cell is hedge,
    * then it is replaced with: 
    * 1 - sword (49 of them)
    * 2 - help (50 of them)
    * 3 - bomb (51 of them)
    * 4 - hydrogen bomb (52 of them)
    *
    *     Spiders:
    * 6 - Black (54 of them)
    * 7 - Blue (55 of them)
    * 8 - Brown (56 of them)
    * 9 - Green (57 of them)
    * : - Grey (58 of them)
    * ; - Orange (59 of them)
    * < - Red (60 of them)
    * = - Yellow (61 of them)
    * 	  
    *     Warrior Exit
    * ? - 1 Spartan warrior exit     
    *
    * The number of items is the decimal representation of character
    * 
    * Using this method we'll initialize our Spiders
    */
    int spiderNumber = 0;
    private void addFeature(char feature, char replace, int number){
        int counter = 0;
        
        while (counter < feature){
            int row = (int) (maze.length * Math.random());
            int col = (int) (maze[0].length * Math.random());
            
            if (maze[row][col] == replace){
                maze[row][col] = feature;
                
                // If spider
                if(feature > '\u0035' && feature < '\u003E'){
                    spiderNumber++;
                    Spider s = new Spider(spiderNumber, feature, row, col);
                    // setting for spider's characteristics
                    switch (feature){
                        case '\u0036':
                            s.setLife();
                            s.setAnger(2);
                            s.setPower(0);
                            s.setDefence(2);
                            break;
                        case '\u0037':
                            s.setLife();
                            s.setAnger(1);
                            s.setPower(0);
                            s.setDefence(2);
                            break;
                        case '\u0038':
                            s.setLife();
                            s.setAnger(1);
                            s.setPower(1);
                            s.setDefence(1);
                            break;
                        case '\u0039':
                            s.setLife();
                            s.setAnger(0);
                            s.setPower(0);
                            s.setDefence(2);
                            break;
                        case '\u003A':
                            s.setLife();
                            s.setAnger(0);
                            s.setPower(2);
                            s.setDefence(0);
                            break;
                        case '\u003B':
                            s.setLife();
                            s.setAnger(2);
                            s.setPower(1);
                            s.setDefence(0);
                            break;
                        case '\u003C':
                            s.setLife();
                            s.setAnger(2);
                            s.setPower(2);
                            s.setDefence(0);
                            break;
                        case '\u003D':
                            s.setLife();
                            s.setAnger(0);
                            s.setPower(1);
                            s.setDefence(1);
                            break;
                        default:
                            s.setLife();
                            s.setAnger(2);
                            s.setPower(2);
                            s.setDefence(0);
                            break;
                    }
                    spiders.addSpider(s);
                }
                counter++;
            }
        }
    }

    // This method randomly populated the maze with empty spaces
    private void buildMaze(){ 
        for (int row = 1; row < maze.length - 1; row++){
            for (int col = 1; col < maze[row].length - 1; col++){
                int num = (int) (Math.random() * 10);
                if (num > 5 && col + 1 < maze[row].length - 1){
                    maze[row][col + 1] = '\u0020'; //\u0020 = 0x20 = 32 (base 10) = SPACE
                }else{
                    if (row + 1 < maze.length - 1)maze[row + 1][col] = '\u0020';
                }
            }
        }
    }

    // returns maze
    public char[][] getMaze(){
        return this.maze;
    }

    // get sell content
    public char get(int row, int col){
        return this.maze[row][col];
    }

    // Sets the given cell with item that is representing by char c
    public void set(int row, int col, char c){
        this.maze[row][col] = c;
    }

    // returns maze length in cells
    public int size(){
        return this.maze.length;
    }

    // maze in string representation for console
    public String toString(){
        StringBuffer sb = new StringBuffer();
        for (int row = 0; row < maze.length; row++){
            for (int col = 0; col < maze[row].length; col++){
                sb.append(maze[row][col]);
                //if (col < maze[row].length - 1) sb.append(",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // returns list of spiders
    public SwarmOfSpiders getSpiders() {
        return this.spiders;
    }
}