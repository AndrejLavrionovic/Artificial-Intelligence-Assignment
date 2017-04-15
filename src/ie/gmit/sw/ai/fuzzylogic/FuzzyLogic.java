package ie.gmit.sw.ai.fuzzylogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyLogic {

	
	public double engage(double weapon, double angerLevel, double playerLife, double spriteLife) {
		
		double lifeForce = 0;
		
		return lifeForce; // To return a double value...
	}
	
	
	
	public static void main(String[] args) {
		
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
	}
}