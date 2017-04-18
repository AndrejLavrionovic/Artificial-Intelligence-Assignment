package ie.gmit.sw.ai;

/**
 * @author Will Hogan G00318460
 */
import java.util.ArrayList;
import java.util.List;


public class SpartanWarrior {

	private double lifeForce = 100; // For example, can be changed to 1000 if needed
	private int currentRow;
	private int currentCol;
	private List<Weapon> weaponList = new ArrayList<Weapon>();

	
	public SpartanWarrior(int currentRow, int currentCol) {
		super();
		getLifeForce();
		this.currentRow = currentRow;
		this.currentCol = currentCol;
	}

	public SpartanWarrior() {
		getLifeForce();
	}
	
	public void setDamageTaken(double damage){
		lifeForce = lifeForce - damage;
		if (! (lifeForce > 0) ) {
			System.out.println("Is Alive? " + isAlive());
			System.out.println("Game Over, You Died!");
			System.exit(0);
		}
	}
	
	// Getters and setters for the Spartan Warriors current location
	public int getCurrentRow() {
		return currentRow;
	}
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}
	public int getCurrentColumn() {
		return currentCol;
	}
	public void setCurrentColumn(int currentCol) {
		this.currentCol = currentCol;
	}

	// Add a Weapon to the back of the Queue
	public void add(Weapon weapon) {
		weaponList.add(weapon);
	}
	
	// Remove a weapon from the front of the queue 
	public void useWeapon() {
		weaponList.remove(1);
	}
	
	// Gets the Spartans LifeForce which is set to 100 at the start of the game
	public double getLifeForce(){
		return lifeForce;
	}
	
	// Check if Alive...
	public boolean isAlive(){
		return lifeForce > 0;
	}
	
	// Decrease the Spartans life
	public double decreaseLife(){
		lifeForce -= 5;
		return lifeForce;
	}
	
	// Increase the Spartans life
	public double increaseLife(){
		lifeForce += 5;
		return lifeForce;
	}
	
	// Display the Spartan's Current Weapons
	public void displayWeapons(){
		for (Weapon weapon : weaponList) {
			System.out.println(weapon);
		}
	}

	// For Testing
	public String toString() {
		return "SpartanWarrior [lifeForce=" + lifeForce + ", currentRow=" + currentRow + ", currentCol=" + currentCol + ", weaponList=" + weaponList + "]";
	}
}