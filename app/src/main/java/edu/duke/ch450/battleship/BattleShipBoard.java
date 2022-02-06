package edu.duke.ch450.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BattleShipBoard<T> implements Board<T> {
  private final int width;
  private final int height;
  private final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  HashSet<Coordinate> enemyMisses;
  final T missInfo;
  protected HashMap<Character, Integer> optionsMap;

  public int getWidth() {
    return this.width;
  };

  public int getHeight() {
    return this.height;
  };

  public BattleShipBoard(int w, int h, T missInfo) {
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
    this.missInfo = missInfo;
    this.optionsMap = initializeOptionsMap();
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
   * @param toAdd is the ship you want to add to the board.
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
   * @param where is the coordinate you want to get the value
   * @param isSelf is the boolean to determine whether it is me or the enemy.(me is true, enemy is false)
   */
  
  public HashMap<String, Integer> collectSona(HashSet<Coordinate> sonaSet, HashMap<String, Integer> resultMap){
    for(Coordinate coordi : sonaSet){
      for(Ship<T> s : myShips){
        if(s.occupiesCoordinates(coordi)){
          resultMap.put(s.getName(),resultMap.get(s.getName())+1);
        } 
    }
    }
    return resultMap;
    
  }
  
  public T whatIsAt(Coordinate where, boolean isSelf){
    if(where.getColumn() > width - 1 || where.getRow() > height - 1){
      throw new IllegalArgumentException("The coordinate is out of bound ");
    }
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    if(isSelf == false && enemyMisses.contains(where)){
      return missInfo;
    }
    return null;
  }

  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }

  
  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  public Ship<T> getShipFromCoordinate(Coordinate coordi){
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(coordi)) {
        return s;
      }
    }
    return null;
    
  }

  /*
   * allSink() is to check whether all the ships on the board are sunk or not
   * It will use the isSunk function to check whether the condition of all ships
*/
  public boolean allSunk(){
    int counter = 0;
    for(Ship<T> ship : myShips){
      if(ship.isSunk()){
        counter++;
      }
    }
    if(counter == myShips.size()){
      return true;
    }
    return false;
  }

  public HashMap<Character, Integer> initializeOptionsMap(){
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    map.put('F', 1000000);
    map.put('M', 3);
    map.put('S', 3);
    return map;
  }

  public HashMap<Character, Integer> getOptionsMap(){
    return this.optionsMap;
  }

}
