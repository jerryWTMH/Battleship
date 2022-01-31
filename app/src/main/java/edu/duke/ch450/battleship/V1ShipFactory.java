package edu.duke.ch450.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character> {
  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
    if(where.getOrientation() == 'V'){
      int temp = w;
      w = h;
      h = temp;
    }
    RectangleShip<Character> s1 = new RectangleShip<Character>(name, where.getWhere(), w, h, letter,'*');
    return s1;
  }
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 2, 1, 's', "Submarine");
  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 4, 1, 'b', "Battleship");
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 6, 1, 'c', "Carrier");
  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 3, 1, 'd', "Destroyer");
  }
  
}
