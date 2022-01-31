package edu.duke.ch450.battleship;

import java.util.ArrayList;

public class BattleShipBoard<T> implements Board<T>{
  private final int width;
  private final int height;
  private final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  public int getWidth(){
    return this.width;
  };  
  public int getHeight(){
    return this.height;
  };
  public BattleShipBoard(int w, int h){
    /**
     * Constructs a BattleShipBoard with the specified width and height
     *  @param w is the width  of the newly constructed board.
     * @param h is the height of the newly constructed board.
     * @throws IIllegalArgumentException if the width or height are less than or equal to zero.
     */
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null) );
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }

  }
  /**
   *Try to add ship into the ship ArrayList!
   */

  public boolean tryAddShip(Ship<T> toAdd){
    if(placementChecker.checkPlacement(toAdd, this) == false){
      throw new IllegalArgumentException("The ship cannot be added into the board! Because it violates the Rules");
    }
    this.myShips.add(toAdd);
    return true;
  }

  /**
   * Get what is the value of the element at input coordinate
   */
  public T whatIsAt(Coordinate where) {
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)){
        return s.getDisplayInfoAt(where);
      }
    }
    return null;
  }

  
}
