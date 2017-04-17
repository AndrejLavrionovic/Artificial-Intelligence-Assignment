package ie.gmit.sw.ai;


/**
 * @author Will Hogan G00318460
 */
import java.util.LinkedList;
import java.util.Queue;

public class SpartanWarrior {

	private int lifeForce = 100;
	private int angerLevel;
	private int weaponAmount;
	private int currentRow;
	private int currentColumn;
	private Queue<Weapon> weaponList = new LinkedList<Weapon>();
	
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


	// Add a Weapon
	public void add(Weapon weapon){
		weaponList.add(weapon);
	}
	
	public int getAngerLevel() {
		return angerLevel;
	}

	public void setAngerLevel(int angerLevel) {
		this.angerLevel = angerLevel;
	}	
	
	public int getLifeForce(){
		return lifeForce;
	}
	
	public int decreaseLife(){
		lifeForce -= 5;
		return lifeForce;
	}
	
	public int increaseLife(){
		lifeForce += 5;
		return lifeForce;
	}

	public int getWeaponAmount() {
		return weaponAmount;
	}

	public void setWeaponAmount(int weaponAmount) {
		this.weaponAmount = weaponAmount;
	}
	
	public void displayWeapons(){
		for (Weapon weapon : weaponList) {
			System.out.println(weapon);
		}
	}

	public String toString() {
		return "SpartanWarrior [lifeForce=" + lifeForce + ", angerLevel=" + angerLevel + ", weaponAmount="
				+ weaponAmount + ", weaponList=" + weaponList + "]";
	}
}