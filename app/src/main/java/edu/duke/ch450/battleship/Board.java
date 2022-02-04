package edu.duke.ch450.battleship;

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
  public boolean allSunk();
  
}
