package edu.duke.ch450.battleship;

public interface Board<T> {
  public int getWidth();
  public int getHeight();
  public boolean tryAddShip(Ship<T> toAdd);

  /**
   * Get what is the value of the element at input coordinate
   */
  public T whatIsAt(Coordinate where);
}
