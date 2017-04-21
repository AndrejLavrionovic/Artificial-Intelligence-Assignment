package ie.gmit.sw.ai;

import ie.gmit.sw.ai.spiders.ControllersPool;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GameRunner implements KeyListener {
    
    // Properties
    private static final int MAZE_DIMENSION = 100; // 100 cells
    private static final int IMAGE_COUNT = 14; // items number (array length)
    private GameView view; // Instance for Game view
    private Maze model; // Instance for maze
    
    // current row and col indicates the Spartan Warrior position
    private int currentRow;
    private int currentCol;
    
    // Instance for the thread pool
    private ControllersPool controller;
    
    // In Game Instance Variables
    private SpartanWarrior spartanWarrior;
    private Weapon weapon;
    

    // Game runner constructor
    public GameRunner() throws Exception{
    	
    	spartanWarrior = new SpartanWarrior(currentRow, currentCol);
    	weapon = new Weapon(WeaponEnum.FIST, 5);
    	spartanWarrior.add(weapon);
    	
        // initialization of Maze, GameView, and Thread pool
        model = new Maze(MAZE_DIMENSION);
        
        view = new GameView(model);
        this.controller = new ControllersPool(model, spartanWarrior);

        // test
        System.out.println(model.toString());
        System.out.println("\n\n==> Spiders number is " + model.getSpiders().getSpidersNumber());

        /*
        * Array of features including sprites, weapons and other itmes:
        * 0 -> Hedge
        * 1 -> Sword
        * 2 -> Help
        * 3 -> Bomb
        * 4 -> Hydrogen Bomb
        * 5 -> Spartan Warrior
        * 6 -> Black Spider
        * 7 -> Blue Spider
        * 8 -> Brown Spider
        * 9 -> Green Spider
        * 10 -> Grey Spider
        * 11 -> Orange Spider
        * 12 -> Red Spider
        * 13 -> Yellow Spider
        */
        Sprite[] sprites = getSprites();
        view.setSprites(sprites); // passes the array to the GameView

        // Randomly places the spartan warrior withing the maze
        placePlayer();

        // This block sets JComponent properties
        // Dimension is set as 800 px in GameView class =>
        // public static final int DEFAULT_VIEW_SIZE = 800;
        Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
        view.setPreferredSize(d);
        view.setMinimumSize(d);
        view.setMaximumSize(d);

        // Adjustments for JFrame (game window on the screen)
        JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);

        // Moving the spiders
        this.controller.doControlling();
    }

    // Picks randome coordinates and places the Spartan Warrior in the maze
    private void placePlayer(){   	
        currentRow = (int) (MAZE_DIMENSION * Math.random());
        currentCol = (int) (MAZE_DIMENSION * Math.random());
        
        model.set(currentRow, currentCol, '5'); //A Spartan warrior is at index 5
        updateView(); // changes the view relatively to the Spartan Warrior		
    }

    /*
    * View is focused on the Spartan Warrior
    * currentRow and currentCol will indicate the position of Spartan Warrior
    */
    private void updateView(){
        view.setCurrentRow(currentRow);
        view.setCurrentCol(currentCol);
        
        spartanWarrior.setCurrentRow(currentRow);
        spartanWarrior.setCurrentCol(currentCol);
    }

    /*
    * Moveing the Spartan Warrior
    */
    public void keyPressed(KeyEvent e) {
    	
        // If right button is clicked and current position of
        // Spartan is less then 99 (in ohter words there is at least one
        // space to move to the right)
        // Nested if => if there is no any item on the next right cell
        // then do the move -> isValidMove() and update the currentView
        // -> currentView++ .
        // Same for the next buttons.
        // Button Z => changes the view
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
                if (isValidMove(currentRow, currentCol + 1)) currentCol++;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
                if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
                if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
                if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
        }else if (e.getKeyCode() == KeyEvent.VK_Z) {
                view.toggleZoom();
        }else{
                return;
        }
        
        updateView(); // changes the view relatively to the Spartan Warrior
    }

    public void keyReleased(KeyEvent e) {} //Ignore
    public void keyTyped(KeyEvent e) {} //Ignore

    /*
    * Checks if there is valid cell and if it is, then moves the herro to that cell
    * and returns true
    * Doing nothing in opposite case and returns false
    * 
    * I would change this method for validating availability for the cell only
    * and created another method move() for moving
    */
    private boolean isValidMove(int row, int col) {
    	
    	Random rand = new Random();
		int randValue = 0;
		
    	char sword = '\u0031', questionMark = '\u0032', bomb = '\u0033', hydrogenBomb = '\u0034', blankSpace = '\u0020';
    	
    	/* Checks what happens when the Spartan Warrior walks over a Weapon
    	 * 1. Create a Weapon object
    	 * 2. Take the Weapon and replace that area with a blank space
    	 * 3. Add the collected weapon to the Weapon ArrayList
    	 * 4. Display Current Weapons */
    	if (row <= model.size() - 1 && col <= model.size() -1 && model.get(row, col) == sword) { 
    		weapon = new Weapon(WeaponEnum.SWORD, 15); 
    		System.out.println("Spartan Warrior Collected Sword");
    		model.set(row, col, blankSpace); 
    		spartanWarrior.add(weapon); 
    		spartanWarrior.displayWeapons();  
    	}
    	if (row <= model.size() - 1 && col <= model.size() -1 && model.get(row, col) == bomb) { 
    		weapon = new Weapon(WeaponEnum.BOMB, 40); 
    		System.out.println("Spartan Warrior Collected Item Bomb");
    		model.set(row, col, blankSpace); 
    		spartanWarrior.add(weapon); 
    		spartanWarrior.displayWeapons();
    	}
    	if (row <= model.size() - 1 && col <= model.size() -1 && model.get(row, col) == hydrogenBomb) { 
    		weapon = new Weapon(WeaponEnum.HYDROGENBOMB, 75); 
    		System.out.println("Spartan Warrior Collected Item Hydrogen Bomb");
    		model.set(row, col, blankSpace); 
    		spartanWarrior.add(weapon); 
    		spartanWarrior.displayWeapons();
    	}
    	if (row <= model.size() - 1 && col <= model.size() -1 && model.get(row, col) == questionMark) { 
    		randValue = rand.nextInt(100 - 1 + 1) + 1;
    		weapon = new Weapon(WeaponEnum.randomWeapon(), randValue);
    		System.out.println("Spartan Warrior Collected Item Random Weapon with a Random Value");
    		model.set(row, col, blankSpace);
    		spartanWarrior.add(weapon);
    		spartanWarrior.displayWeapons();
    	}
    	
    	// If the Current row and col are blank, then move the spartan warrior to that location
        if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col) == ' ') {
            model.set(currentRow, currentCol, '\u0020');
            model.set(row, col, '5');
            return true;
        }
        else {
            return false; //Can't move
        }
    }
    
    

    // Method populates the itime arrey
    // Index in the array is equalent to the char that represents the item.
    private Sprite[] getSprites() throws Exception{
        
        //Read in the images from the resources directory as sprites. Note that each
        //sprite will be referenced by its index in the array, e.g. a 3 implies a Bomb...
        //Ideally, the array should dynamically created from the images... 
        Sprite[] sprites = new Sprite[IMAGE_COUNT];
        sprites[0] = new Sprite("Hedge", "resources/hedge.png");
        sprites[1] = new Sprite("Sword", "resources/sword.png");
        sprites[2] = new Sprite("Help", "resources/help.png");
        sprites[3] = new Sprite("Bomb", "resources/bomb.png");
        sprites[4] = new Sprite("Hydrogen Bomb", "resources/h_bomb.png");
        sprites[5] = new Sprite("Spartan Warrior", "resources/spartan_1.png", "resources/spartan_2.png");
        sprites[6] = new Sprite("Black Spider", "resources/black_spider_1.png", "resources/black_spider_2.png");
        sprites[7] = new Sprite("Blue Spider", "resources/blue_spider_1.png", "resources/blue_spider_2.png");
        sprites[8] = new Sprite("Brown Spider", "resources/brown_spider_1.png", "resources/brown_spider_2.png");
        sprites[9] = new Sprite("Green Spider", "resources/green_spider_1.png", "resources/green_spider_2.png");
        sprites[10] = new Sprite("Grey Spider", "resources/grey_spider_1.png", "resources/grey_spider_2.png");
        sprites[11] = new Sprite("Orange Spider", "resources/orange_spider_1.png", "resources/orange_spider_2.png");
        sprites[12] = new Sprite("Red Spider", "resources/red_spider_1.png", "resources/red_spider_2.png");
        sprites[13] = new Sprite("Yellow Spider", "resources/yellow_spider_1.png", "resources/yellow_spider_2.png");
        // sprites[14] = new Sprite("Exit", "resources/spartanExit.png");
       
        return sprites;
    }

    // Main method
    public static void main(String[] args) throws Exception{
        new GameRunner(); // run the game
    }
}