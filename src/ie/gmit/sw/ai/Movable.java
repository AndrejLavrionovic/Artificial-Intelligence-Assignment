package ie.gmit.sw.ai;

import ie.gmit.sw.ai.spiders.Spider;

/**
 *
 * @author Anrej Lavrinovic
 *
 * Interface contains method move that going to be implemented
 * for spiders and for Warrior
 */
public interface Movable {
    void move(int row, int col, Spider spider);
}
