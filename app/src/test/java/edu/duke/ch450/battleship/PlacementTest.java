package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  @Test
  public void test_getter() {
    Coordinate coordi = new Coordinate(1, 2);
    Coordinate coordi2 = new Coordinate(1, 2);
    Placement p = new Placement(coordi, 'V');
    assertEquals(p.getOrientation(), 'V');
    assertEquals(p.getWhere(), coordi2);
  }

  @Test void test_case_sensitive(){
    Coordinate coordi = new Coordinate(1,2);
    Placement p = new Placement(coordi, 'v');
    assertEquals(p.getOrientation(), 'V');
    assertThrows(IllegalArgumentException.class, ()-> new Placement(coordi, 'z'));
  }

  @Test
  public void test_toString() {
    Placement p = new Placement("A2V");
    assertEquals("(0,2)V", p.toString());
  }

  @Test
  public void test_equals() {
    Placement p1 = new Placement("A2V");
    Placement p2 = new Placement("A2V");
    Placement p3 = new Placement("B3H");
    Placement p4 = new Placement("B3V");
    assertEquals(p1, p2);
    assertEquals(p1, p1);
    assertNotEquals(p1, p3);
    assertNotEquals(p1, p4);
    assertNotEquals(p4, "B3");
  }

  /**
   * The first two testcases are testing about whether the input of Placement is too short or contains invalid character.
  */

  @Test
  public void strConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new Placement("B0"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("B0Z"));
    Placement p1 = new Placement("B0V");
    Placement p2 = new Placement("B0v");
    Placement p3 = new Placement("C7V");
    assertEquals(p1, p2);
    assertNotEquals(p1, p3);
    assertNotEquals(p2, p3);
  }

  @Test
  public void test_hashCode() {
    Placement p1 = new Placement("A2V");
    Placement p2 = new Placement("A2V");
    Placement p3 = new Placement("A1H");
    Placement p4 = new Placement("A2H");
    assertEquals(p1.hashCode(), p2.hashCode());
    assertNotEquals(p2.hashCode(), p3.hashCode());
    assertNotEquals(p2.hashCode(), p4.hashCode());
    assertNotEquals(p4.hashCode(), p3.hashCode());

  }

  @Test
  public void test_invalid_placement(){
     assertThrows(IllegalArgumentException.class, ()-> new Placement(new Coordinate(0,0), 'H', "Carrier"));
     assertThrows(IllegalArgumentException.class, ()-> new Placement(new Coordinate(0,0), 'H', "Carrier"));
     assertThrows(IllegalArgumentException.class, ()-> new Placement(new Coordinate(0,0), 'U', "Destroyer"));
     Placement p1 = new Placement(new Coordinate(0,0), 'H', "Submarine");     
  }

  /**
     Placement(Coordinate coordi, Character ori, String shipName)
*/
  @Test
  public void test_constructor(){
     assertThrows(IllegalArgumentException.class, ()-> new Placement(new Coordinate(0,0), 'H',"Battleship"));
     assertThrows(IllegalArgumentException.class, ()-> new Placement(new Coordinate(1,0), 'Z',"Destroyer"));
     Placement p1 = new Placement(new Coordinate(0,2), 'H', "Submarine");
  }
}
