package ie.gmit.sw.ai;


/**
 * Author Will Hogan G00318460
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum WeaponEnum {

	FIST("FIST"),
	SWORD("SWORD"), 
	BOMB("BOMB"), 
	HYDROGENBOMB("HYDROGENBOMB");
	
	private final String name;
	
	private WeaponEnum(String name) {
		this.name = name;
	}
	
	public boolean equalsName(String otherName){
		return name.equals(otherName);
	}
	
	public String toString(){
		return this.name;
	}
	
	private static final List<WeaponEnum> WEAPONENUMLIST = Collections.unmodifiableList(Arrays.asList(SWORD, BOMB, HYDROGENBOMB));
	private static final int SIZE = WEAPONENUMLIST.size();
	private static final Random RANDOM = new Random();
	
	public static WeaponEnum randomWeapon(){
		return WEAPONENUMLIST.get(RANDOM.nextInt(SIZE));
	}
}