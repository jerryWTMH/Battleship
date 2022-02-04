package edu.duke.ch450.battleship;

public interface Board<T> {
  public int getWidth();
  public int getHeight();
  public String tryAddShip(Ship<T> toAdd);

  /**
   * Get what is the value of the element at input coordinate
   */
  public T whatIsAtForSelf(Coordinate where);

  /*
   * 
*/
  public Ship<T> fireAt(Coordinate c);
  public T whatIsAtForEnemy(Coordinate where);
}
