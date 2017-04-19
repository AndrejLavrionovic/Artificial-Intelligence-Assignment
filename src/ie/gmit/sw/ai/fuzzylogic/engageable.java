package ie.gmit.sw.ai.fuzzylogic;

import ie.gmit.sw.ai.SpartanWarrior;
import ie.gmit.sw.ai.Weapon;
import ie.gmit.sw.ai.spiders.Spider;

public interface engageable {

	public abstract double engage(SpartanWarrior spartanWarrior, Spider spider, Weapon weapon);
}