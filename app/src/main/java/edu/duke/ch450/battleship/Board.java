package edu.duke.ch450.battleship;

import java.util.HashMap;

public interface Board<T> {
  public int getWidth();
  public int getHeight();
  public String tryAddShip(Ship<T> toAdd);

  /**
   * Get what is the value of the element at input coordinate of myself
   * @param where is the coordinate to check
   * @throws IllegalArgumentException if where is out of bound
   */
  public T whatIsAtForSelf(Coordinate where);

  /**
   * This receives a fire at specific coordinate
   * @param where is the coordinate to check
*/
  public Ship<T> fireAt(Coordinate c);

  /**
   *This returns the coordinate information for enemy
   * @param where is the coordinate to check
*/
  public T whatIsAtForEnemy(Coordinate where);

  /**
   * allSunk helps us to make sure whether whole ships are sunk or not
   * true is allsunk, false means there are still some ships alive.
*/
  public boolean allSunk();
  /**
   * getOptionsMap can help us to get the counter for each options
   * ex: remaining 3 for Sona, remaining 1 for moving a ship
*/
  public HashMap<Character, Integer> getOptionsMap();
  public HashMap<Character, Integer> initializeOptionsMap();
}
