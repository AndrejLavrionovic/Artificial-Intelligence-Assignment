package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;

public class Weapon {

	private static List<WeaponEnum> listOfWeapons = new ArrayList<WeaponEnum>();
	private double weaponPower;
	private int[][] weaponLocation;
	private String name;
	
	public void addWeapons(){
		listOfWeapons.add(WeaponEnum.BOMB);
		listOfWeapons.add(WeaponEnum.HYDROGENBOMB);
		listOfWeapons.add(WeaponEnum.SWORD);
	}
	
	public void display(){
		for (WeaponEnum weaponEnum : listOfWeapons) {
			System.out.println(weaponEnum);
		}
	}
}