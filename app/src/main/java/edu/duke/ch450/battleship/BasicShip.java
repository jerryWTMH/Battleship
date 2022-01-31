
package edu.duke.ch450.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T>{
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected HashMap<Coordinate, Boolean> myPieces;
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo){
    this.myDisplayInfo = myDisplayInfo;
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
  public T getDisplayInfoAt(Coordinate where) {
    //TODO this is not right.  We need to
    //look up the hit status of this coordinate
   checkCoordinateInThisShip(where);
   return myDisplayInfo.getInfo(where, wasHitAt(where));
  }
}
