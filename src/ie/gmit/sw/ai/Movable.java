/*
 * Interface contains method move that going to be implemented
 * for spiders and for Warrior
 */
package ie.gmit.sw.ai;

import ie.gmit.sw.ai.spiders.Spider;

/**
 *
 * @author Anrej Lavrinovic, Will Hogan
 */
public interface Movable {
    void move(int row, int col, Spider spider);
}
