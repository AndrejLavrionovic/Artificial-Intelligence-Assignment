/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.gmit.sw.ai;

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
    public int getSpidersnumber(){
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
