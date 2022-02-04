
package edu.duke.ch450.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T>{
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> enemyDisplayInfo;
  
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo){
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
    myPieces = new HashMap<Coordinate, Boolean>();
    for(Coordinate c : where){
      myPieces.put(c, false);
    }
  }

  protected void checkCoordinateInThisShip(Coordinate c){
    if(myPieces.get(c) == null){
      throw new IllegalArgumentException("The Coordinate doesn't exist in the ship!");
    }
  }
  
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    if(myPieces.get(where) == null){
      return false;
    }
    return true;
  }

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    for(Coordinate coordi: myPieces.keySet()){
      checkCoordinateInThisShip(coordi);
      if(myPieces.get(coordi) == false){
        return false;
      }
    }    
    return true;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
   checkCoordinateInThisShip(where);
   myPieces.put(where, true);
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    if(myPieces.get(where) == true){
      return true;
    }
    return false;
  }

 @Override
 public T getDisplayInfoAt(Coordinate where, boolean myShip) {
    //TODO this is not right.  We need to
    //look up the hit status of this coordinate
   checkCoordinateInThisShip(where);
   if(myShip == true){
     return myDisplayInfo.getInfo(where, myPieces.get(where));
   }
   return enemyDisplayInfo.getInfo(where, myPieces.get(where));
  }

  @Override
  public Iterable<Coordinate> getCoordinates(){
    return myPieces.keySet();
  }


  
}
