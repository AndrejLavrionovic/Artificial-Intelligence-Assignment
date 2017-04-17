package ie.gmit.sw.ai;


/**
 * @author Will Hogan G00318460
 */
import java.util.LinkedList;
import java.util.Queue;

public class SpartanWarrior {

	private double lifeForce = 100;
	private int angerLevel; //  Need to figure out how to use this instance variable
	// private int weaponAmount; // Not needed as we'll just use a Queue that can contain duplicates
	private int currentRow;
	private int currentColumn;
	private Queue<Weapon> weaponList = new LinkedList<Weapon>();
	
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
		return currentColumn;
	}
	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
	}

	// Add a Weapon to the back of the Queue
	public void add(Weapon weapon){
		weaponList.offer(weapon);
	}
	
	// Remove a weapon from the front of the queue 
	public void useWeapon() {
		weaponList.poll();
	}
	
	// Getters and Setters for the Spartan Warriors Anger Level
	public int getAngerLevel() {
		return angerLevel;
	}

	public void setAngerLevel(int angerLevel) {
		this.angerLevel = angerLevel;
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
		return "SpartanWarrior [lifeForce=" + lifeForce + ", angerLevel=" + angerLevel + ", weaponList=" + weaponList + "]";
	}
	
	/*	
	public int getWeaponAmount() {
		return weaponAmount;
	}
	
	public void setWeaponAmount(int weaponAmount) {
		this.weaponAmount = weaponAmount;
	}*/
}