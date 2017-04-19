package ie.gmit.sw.ai.fuzzylogic;


/**
 * @author Will Hogan G00318460
 */

import ie.gmit.sw.ai.SpartanWarrior;
import ie.gmit.sw.ai.Weapon;
import ie.gmit.sw.ai.spiders.Spider;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;


public class FuzzyLogic implements engageable {
	
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
}