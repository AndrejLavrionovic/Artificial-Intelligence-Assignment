package ie.gmit.sw.ai;


/**
 * @author Will Hogan G00318460
 */
public class Weapon {

	private WeaponEnum weaponEnum;
	private double weaponPower;
	
	public Weapon() {
	}

	public Weapon(WeaponEnum weaponEnum, double weaponPower) {
		super();
		this.weaponEnum = weaponEnum;
		this.weaponPower = weaponPower;
	}

	public double getWeaponPower() {
		return weaponPower;
	}
	
	public void setWeaponPower(double weaponPower) {
		this.weaponPower = weaponPower;
	}

	public WeaponEnum getWeaponEnum() {
		return weaponEnum;
	}

	public void setWeaponEnum(WeaponEnum weaponEnum) {
		this.weaponEnum = weaponEnum;
	}

	public String toString() {
		return "Weapon [weaponEnum=" + weaponEnum + ", weaponPower=" + weaponPower + "]";
	}
	
	
	
	
//////////   TESTING   ///////////////////////////////////////////////////////////////////////
	/*
	// private static List<WeaponEnum> listOfWeapons = new ArrayList<WeaponEnum>();
	public void addWeapons(){
		listOfWeapons.add(WeaponEnum.BOMB);
		listOfWeapons.add(WeaponEnum.HYDROGENBOMB);
		listOfWeapons.add(WeaponEnum.SWORD);
	}
	
	public void display(){
		for (WeaponEnum weaponEnum : listOfWeapons) {
			System.out.println(weaponEnum);
		}
	}*/
	
//////////////////////////////////////////////////////////////////////////////////////////////
}


