package ie.gmit.sw.ai;

public class TestRunner {

	public static void main(String[] args) {
		
		// For Mock class testing services...
		
		SpartanWarrior spartanWarrior = new SpartanWarrior();
		Weapon weapon = new Weapon(WeaponEnum.BOMB, 40);
		Weapon weapon2 = new Weapon(WeaponEnum.SWORD, 15);
		
		
		spartanWarrior.add(weapon);
		spartanWarrior.add(weapon2);
		
		spartanWarrior.displayWeapons();
	}
}