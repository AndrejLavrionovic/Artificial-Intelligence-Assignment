package ie.gmit.sw.ai.fuzzylogic;


/**
 * @author Will Hogan G00318460
 */

import ie.gmit.sw.ai.SpartanWarrior;
import ie.gmit.sw.ai.Weapon;
import ie.gmit.sw.ai.WeaponEnum;
import ie.gmit.sw.ai.spiders.Spider;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class FuzzyLogic {

	private static Weapon weapon = new Weapon(WeaponEnum.BOMB, 75);
	private static SpartanWarrior spartanWarrior = new SpartanWarrior();

	/*
	public static void main(String[] args) {
		
		FuzzyLogic fuzzyLogic = new FuzzyLogic();
		fuzzyLogic.engage(spartanWarrior, 50.0, weapon);
		
		System.out.println();
		
		fuzzyLogic.engage(spartanWarrior, 60.0, weapon);
		
		System.out.println();
		
		fuzzyLogic.engage(spartanWarrior, 40.0, weapon);

		System.out.println();
		
		fuzzyLogic.engage(spartanWarrior, 40.0, weapon);
		
		System.out.println();
		
		fuzzyLogic.engage(spartanWarrior, 40.0, weapon);
		
	}
	*/
	
	
	// Need to pass in a Spartan Warrior, Weapon and Spider instances into the engage method
	// The Spider and Spartan will both have life instance variables, Anger is associated with the Spartan and the Spartan will also have (HAS-A) Weapon
	public double engage(SpartanWarrior spartanWarrior, Spider spider, Weapon weapon) {
		
		FIS fis = FIS.load("./engage.fcl", true);
		FunctionBlock fb = fis.getFunctionBlock("engage");
		// JFuzzyChart.get().chart(fb); // Print all Charts to Screen
		fis.setVariable("weapon", weapon.getWeaponPower());
		fis.setVariable("playerLife", spartanWarrior.getLifeForce());
		fis.setVariable("spriteLife", spider.getLife());
		fis.evaluate(); // THE FUZZY INFERENCE ENGINE FIRES UP HERE. 
		
		double crispOutputResult = fis.getVariable("chanceToWin").getValue();

		double damage = 100 - crispOutputResult;

        System.out.println("Current Spartan Warriors Health: " + spartanWarrior.getLifeForce());
        System.out.println("Damage is: " + damage);
        System.out.println("Crisp Output Result is :" + crispOutputResult); // Display crisp output only, without charts

		return damage;
	}
	
	
	// The below code will need to be manipulated into the above engage method
	/*public static void main(String[] args) {
		
		int spriteHealth = 1;
		int playerHealth = 1;
		double result = 0.0;
		
		FIS fis = FIS.load("./engage.fcl", true);
		FunctionBlock fb = fis.getFunctionBlock("engage");
		// JFuzzyChart.get().chart(fb); // Print all Charts to Screen
		fis.setVariable("weapon", 20);
		fis.setVariable("playerLife", 75);
		fis.setVariable("spriteLife", 50);
		fis.evaluate(); // THE FUZZY INFERENCE ENGINE FIRES UP HERE. 
		
		Variable tip = fb.getVariable("chanceToWin");
		// JFuzzyChart.get().chart(tip.getDefuzzifier(), "Crisp Output", true);  //  Prints only Crisp Output chart  to screen
		result = tip.defuzzify();
		System.out.println(result); // Display crisp output only, without charts
		
		// Testing...
		System.out.println("---------------------------------------------");
		String display = (result > 60) ? spriteHealth-- + " : YOU WON THE FIGHT!" : playerHealth-- + "YOU DIED!";
		System.out.println(display);
		System.out.println("PLAYER HEALTH: " + playerHealth + "\nSPRITE HEALTH: " + spriteHealth);
	}*/
}