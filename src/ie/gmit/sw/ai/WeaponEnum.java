package ie.gmit.sw.ai;

public enum WeaponEnum {

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
}