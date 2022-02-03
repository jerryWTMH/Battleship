package edu.duke.ch450.battleship;

import java.util.ArrayList;
import java.util.HashSet;

public class BattleShipBoard<T> implements Board<T> {
  private final int width;
  private final int height;
  private final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  HashSet<Coordinate> enemyMisses;

  public int getWidth() {
    return this.width;
  };

  public int getHeight() {
    return this.height;
  };

  public BattleShipBoard(int w, int h) {
    /**
     * Constructs a BattleShipBoard with the specified width and height
     * 
     * @param w is the width of the newly constructed board.
     * @param h is the height of the newly constructed board.
     * @throws IIllegalArgumentException if the width or height are less than or
     *                                   equal to zero.
     */
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null));
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.enemyMisses = new HashSet<Coordinate>();

  }

  /**
   * This method should search for any ship that occupies coordinate c. If one is
   * found, that Ship is "hit" by the attack and should record it. Then we should
   * return this ship.
   */

  public Ship<T> fireAt(Coordinate c) {
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(c)) {
        s.recordHitAt(c);
        return s;
      }
    }
     enemyMisses.add(c);
    return null;      
 }
  

  /**
   * Try to add ship into the ship ArrayList!
   */

  public String tryAddShip(Ship<T> toAdd) {
    String placementProblem = placementChecker.checkPlacement(toAdd, this);
    if (placementProblem != null) {
      return placementProblem;
    }
    this.myShips.add(toAdd);
    return null;
  }

  /**
   * Get what is the value of the element at input coordinate
   */
  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }

  protected T whatIsAt(Coordinate where, boolean isSelf){
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        return s.getDisplayInfoAt(where);
      }
    }
    return null;
  }

}
