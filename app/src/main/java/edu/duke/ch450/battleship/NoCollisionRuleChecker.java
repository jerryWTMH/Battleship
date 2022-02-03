package edu.duke.ch450.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
  public NoCollisionRuleChecker(PlacementRuleChecker<T> next){
    super(next);
  }
  
  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub
    Iterable<Coordinate> coordi = theShip.getCoordinates();
    for(Coordinate c : coordi){
      if(theBoard.whatIsAtForSelf(c) != null){
        return "That placement is invalid: the ship overlaps another ship.";
      }
    }
    return null;
  }
  
}
