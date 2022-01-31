package edu.duke.ch450.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  /**
   *Constructs a rule checker on next rule checker(recursion)
   * @param next is next rule checker
*/
  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  /**
   * Check the rule of where ship is bounded of the Board
   * @param theShip is the ship to place and test
   * @param theBoard is the board to be placed and test
*/
  
  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub
    Iterable<Coordinate> set = theShip.getCoordinates();
    for(Coordinate coordi: set){
      if(coordi.getRow() < 0 || coordi.getRow() >= theBoard.getHeight() || coordi.getColumn() < 0 || coordi.getColumn() >= theBoard.getWidth()){
        return false;
      }
    }
    return true;
  }

}
