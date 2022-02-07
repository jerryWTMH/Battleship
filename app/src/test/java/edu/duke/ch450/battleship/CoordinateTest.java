package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CoordinateTest {
  @Test
  public void test_coordinate_init() {
    Coordinate coor = new Coordinate(3,4);
    assertEquals(3, coor.getRow());
    assertEquals(4, coor.getColumn());
  }

  @Test
  public void test_equals(){
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);
    Coordinate c4 = new Coordinate(3, 2);
    assertEquals(c1, c1);   //equals should be reflexsive
    assertEquals(c1, c2);   //different objects bu same contents
    assertNotEquals(c1, c3);  //different contents
    assertNotEquals(c1, c4);
    assertNotEquals(c3, c4);
    assertNotEquals(c1, "(1, 2)"); //different types
  }

  @Test
  public void test_hashCode(){
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3);
    Coordinate c4 = new Coordinate(2, 1); 
    assertEquals(c1.hashCode(), c2.hashCode());
    assertNotEquals(c1.hashCode(), c3.hashCode());
    assertNotEquals(c1.hashCode(), c4.hashCode());
  }

  @Test
  void test_string_constructor_valid_cases() {
    Coordinate c1 = new Coordinate("B3");
    assertEquals(1, c1.getRow());
    assertEquals(3, c1.getColumn());
    Coordinate c2 = new Coordinate("D5");
    assertEquals(3, c2.getRow());
    assertEquals(5, c2.getColumn());
    Coordinate c3 = new Coordinate("A9");
    assertEquals(0, c3.getRow());
    assertEquals(9, c3.getColumn());
    Coordinate c4 = new Coordinate("Z0");
    assertEquals(25, c4.getRow());
    assertEquals(0, c4.getColumn());
  }

  @Test
  public void test_string_constructor_error_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("00"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("AA"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("@0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("[0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A/"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A:"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A12"));
  }

  @Test
  public void test_boundCheck(){
    Coordinate coordi = new Coordinate(5,5);
    assertEquals(false, coordi.boundCheck(2,2));
    assertEquals(true, coordi.boundCheck(8,8));
  }

  @Test
  public void test_sonaCheck(){
   BattleShipBoard<Character> board = new BattleShipBoard<Character>(8,8,'X');
    V1ShipFactory factory = new V1ShipFactory();
    Coordinate coordi = new Coordinate(1,1);
    assertThrows(IllegalArgumentException.class, ()-> new Placement("A0H", "Battleship"));
     assertThrows(IllegalArgumentException.class, ()-> new Placement("A0U", "Submarine"));
    Placement u0_0 = new Placement("A0U", "Battleship");
    Ship<Character> c = factory.makeBattleship(u0_0);
    board.tryAddShip(c);
    HashMap<String, Integer> result = coordi.sonaCheck(board);
    assertEquals(4, result.get("Battleship"));
    
    
  }

  
}
