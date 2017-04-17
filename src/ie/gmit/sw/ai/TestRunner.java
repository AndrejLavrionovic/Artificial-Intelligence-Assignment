package ie.gmit.sw.ai;

public class TestRunner {

	public static void main(String[] args) {
		
		// For Mock class testing services...
		
		SpartanWarrior spartanWarrior = new SpartanWarrior();
		
		Weapon weapon = new Weapon(WeaponEnum.BOMB, 40);
		Weapon weapon2 = new Weapon(WeaponEnum.SWORD, 15);
		Weapon weapon3 = new Weapon(WeaponEnum.HYDROGENBOMB, 75);
		Weapon weapon4 = new Weapon(WeaponEnum.BOMB, 40);
		
		spartanWarrior.add(weapon);
		spartanWarrior.add(weapon2);
		spartanWarrior.add(weapon3);
		
		spartanWarrior.displayWeapons();
		
		System.out.println();
		spartanWarrior.useWeapon();
		spartanWarrior.displayWeapons();
		
		System.out.println();
		spartanWarrior.add(weapon4);
		spartanWarrior.displayWeapons();
		
		System.out.println();
		spartanWarrior.useWeapon();
		spartanWarrior.displayWeapons();
	}
}