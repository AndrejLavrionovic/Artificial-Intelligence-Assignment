package ie.gmit.sw.ai;

/**
 * @author Will Hogan G00318460
 */
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class SpartanWarrior {

	private double lifeForce = 1000; // For example, can be changed to 1000 if needed
	private int currentRow;
	private int currentCol;
	private List<Weapon> weaponList = new ArrayList<Weapon>();

	// Constructor
	public SpartanWarrior(int currentRow, int currentCol) {
		super();
		getLifeForce();
		this.currentRow = currentRow;
		this.currentCol = currentCol;
	}

	// Get the Spartans LifeForce 
	public SpartanWarrior() {
		getLifeForce();
	}
	
	// Sets the damage taken in battle between Spartan Warrior and Spider
	public void setDamageTaken(double damage){
		lifeForce -= damage;
		if (! (lifeForce > 0) ) {
			System.out.println("Is Alive? " + isAlive());
			System.out.println("Game Over, You Died!");
			JOptionPane.showMessageDialog(null, "GAME OVER");
			// This section will trigger game over events on the screen with game summary
		}
	}
	
	// Getters and setters for the Spartan Warriors current location
	public int getCurrentRow() {
		return currentRow;
	}
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}
	public int getCurrentCol() {
		return currentCol;
	}
	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}

	// Add a Weapon to the ArrayList
	public void add(Weapon weapon) {
		weaponList.add(weapon);
	}
	
	// Remove any other weapon from the ArrayList except for FIST, which must remain
	// If remove is used when list is size 1, trigger else
	public Weapon useWeapon() {
		Weapon weapon = weaponList.get(weaponList.size() - 1);
		if(weaponList.size() > 1){
			weaponList.remove(weaponList.size() - 1);
		}
		else {
			System.out.println("Nothing to Remove, only Fist remains");
		}
		return  weapon;
	}
	
	// Return the Spartan warriors current weapon count
	public int weaponCount(){
		return weaponList.size();
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
	
	public Weapon displayNextWeapon() {
		if(weaponList.size() <= 1)
			return weaponList.get(0);
		return weaponList.get(1);
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