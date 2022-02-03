package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  /**
   * Testting whether the width and height of the Board is correct!
   */
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());   
  }
  /**
   *Test whether the dimension of the board is correct or not
*/
  
  @Test
  public void test_invalid_dimensions(){
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10,0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0,20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10,-5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8,20));
  }

  /**
     Test whether the BattleShipBoard can work correctly when it is just initialized, and the value in the board shold all be null  
*/
  /*@Test
  public void test_whether_blank() {
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10, 20);
    for(int i = 0 ; i < 10; i++){
      for(int j = 0; j < 20; j++){
        Coordinate coordi = new Coordinate(i,j);
        assertEquals(b1.whatIsAt(coordi), null);
      }
    }   
  } */

  @Test
  public void test_each_elements(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(5,5);
    Character[][] expected = new Character[5][5];
    checkWhatIsAtBoard(b1, expected);
    
    Coordinate coordi = new Coordinate(2,3);
    RectangleShip<Character> s1 = new RectangleShip<Character>(coordi, 's', '*');
    b1.tryAddShip(s1);
    expected[2][3] = 's';
    checkWhatIsAtBoard(b1, expected);
    expected[2][2] = 's';
    assertThrows(IllegalArgumentException.class, ()->checkWhatIsAtBoard(b1, expected));
    
  }

  /**
   *This is the helper function to check whether the element in the BattleShipBoard is the same as the T[][] expected!
*/
  @Test
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected){
    for(int i = 0 ; i < expected.length; i++){
      for(int j = 0; j < expected[0].length; j++){
        Coordinate coordi = new Coordinate(i,j);
        if(expected[i][j] != b.whatIsAtForSelf(coordi)){
          throw new IllegalArgumentException("The BattleShipBoard is not the same as expected\n");
        }
        
      }
    }
  }

  @Test
  public void test_try_add_ship_checkrule(){
    BattleShipBoard<Character> board = new BattleShipBoard<Character>(8,8);
    V1ShipFactory factory = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1,2), 'V');
    Ship<Character> c = factory.makeCarrier(v1_2);
    board.tryAddShip(c);

    Placement h5_2 = new Placement(new Coordinate(5,2), 'H');
    Ship<Character> d = factory.makeDestroyer(h5_2);
    assertEquals("That placement is invalid: the ship overlaps another ship.", board.tryAddShip(d));

    Placement v7_2 = new Placement(new Coordinate(7,2), 'V');
    Ship<Character> b = factory.makeBattleship(v7_2);
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.", board.tryAddShip(b));
  }


  @Test
  public void test_fireAt(){
    BattleShipBoard<Character> board = new BattleShipBoard<Character>(9,9);
    V1ShipFactory factory = new V1ShipFactory();
    Coordinate coordi = new Coordinate(2,0);
    Placement h2_0 = new Placement(coordi, 'H');
    Ship<Character> s = factory.makeSubmarine(h2_0);
    board.tryAddShip(s);
    assertSame(s, board.fireAt(coordi));
    board.fireAt(new Coordinate(2,1));
    assertEquals(true, s.isSunk());
    // Testing if the enemy fire at the miss place!
    board.fireAt(new Coordinate(7,7));
    
    
  }
}
