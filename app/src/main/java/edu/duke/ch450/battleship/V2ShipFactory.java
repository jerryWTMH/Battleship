package edu.duke.ch450.battleship;

public class V2ShipFactory extends V1ShipFactory {
  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
     if(name == "Battleship"){
       Battleship<Character> b1 = new Battleship<Character>(where, where.getWhere(), letter,'*');
       return b1;
     } else{
      Carrier<Character> c1 = new Carrier<Character>(where, where.getWhere(), letter,'*');
       return c1;
     }
  }
  @Override
  public Ship<Character> makeBattleship(Placement where) {
    return createShip(where, 4, 1, 'b', "Battleship");
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    return createShip(where, 7, 1, 'c', "Carrier");
  }
  
}
