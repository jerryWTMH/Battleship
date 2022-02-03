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
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub
    Iterable<Coordinate> set = theShip.getCoordinates();
    for(Coordinate coordi: set){
      if(coordi.getRow() < 0){
        return "That placement is invalid: the ship goes off the top of the board.";
      }
      if(coordi.getRow() >= theBoard.getHeight()){
        return "That placement is invalid: the ship goes off the bottom of the board.";
      }
       if(coordi.getColumn() < 0 ){
         return "That placement is invalid: the ship goes off the left of the board.";
      }
       if(coordi.getColumn() >= theBoard.getWidth()){
        return "That placement is invalid: the ship goes off the right of the board.";
      }
    }
    return null;
  }

}
