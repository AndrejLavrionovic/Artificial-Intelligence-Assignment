/*
 * Class is encapsulates the Collection of Spider objects by delegating List methods
 */
package ie.gmit.sw.ai.spiders;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anrej Lavrinovic, Will Hogan
 */
public class SpidersArmy {
    
    // List of spiders
    private List<Spider> spiders = new ArrayList<Spider>();
    
    // Delegated methods
    // return number of spiders in the list
    public int getSpidersNumber(){
        return spiders.size();
    }
    
    // Method adds spider to the list
    public void addSpider(Spider spider){
        spiders.add(spider);
    }
    
    public void removeSpider(Spider spider){
        spiders.remove(spider);
    }

    public Spider getSpiderByIndex(int i) {
        return spiders.get(i);
    }
}
