package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {

  /**
   * This is a simple test to check whether the makeCoords() can work correctly or
   * not? Just change the parameters inside of the rc.makeCoords() Also remember
   * to change to limitation in the for loop
   */
  @Test
  public void test_makeCoords() {
    Coordinate c1 = new Coordinate(0, 1);
    RectangleShip<Character> rc = new RectangleShip<Character>(c1, 's', '*');
    int width = 1;
    int height = 3;
    HashSet<Coordinate> response_set = rc.makeCoords(c1,width,height);
    for (int r = 0; r < 0 + height; r++) {
      for (int c = 1; c < 1 + width; c++) {
        Coordinate temp = new Coordinate(r, c);
        assertTrue(response_set.contains(temp));
      }
    }
  }

  /**
   * This test is for the Constructor
   */
  @Test
  public void test_constructor_coordinate() {
    Coordinate c = new Coordinate(1, 2);
    RectangleShip<Character> s1 = new RectangleShip<Character>(c, 's', '*');
    assertTrue(s1.myPieces.containsKey(c));
  }

  /**
   * This test is also for the Constructor
   */
  @Test
  public void test_constructor_Iterable() {
    Coordinate coordi1 = new Coordinate(1, 2);
    Coordinate coordi2 = new Coordinate(1, 3);
    Coordinate coordi3 = new Coordinate(2, 2);
    Coordinate coordi4 = new Coordinate(2, 3);
    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", coordi1, 2, 2, 's', '*');
    assertTrue(s1.myPieces.containsKey(coordi1));
    assertTrue(s1.myPieces.containsKey(coordi2));
    assertTrue(s1.myPieces.containsKey(coordi3));
    assertTrue(s1.myPieces.containsKey(coordi4));
  }

  @Test
  public void test_recordHit() {
    Coordinate coordi1 = new Coordinate(1, 2);
    Coordinate hit_point = new Coordinate(2, 2);
    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", coordi1, 2, 2, 's', '*');
    s1.recordHitAt(hit_point);
    assertTrue(s1.myPieces.get(hit_point));
    assertFalse(s1.myPieces.get(coordi1));
  }

  @Test
  public void test_wasHitAt() {
    Coordinate coordi1 = new Coordinate(1, 2);
    Coordinate hit_point = new Coordinate(2, 2);
    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", coordi1, 2, 2, 's', '*');
    s1.recordHitAt(hit_point);
    assertTrue(s1.wasHitAt(hit_point));
    assertFalse(s1.wasHitAt(coordi1));
  }

  @Test
  public void test_isSunk() {
    Coordinate coordi1 = new Coordinate(1, 2);
    Coordinate hit_point = new Coordinate(2, 2);
    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", coordi1, 2, 2, 's', '*');
    s1.recordHitAt(hit_point);
    assertFalse(s1.isSunk());
    Coordinate hit_point2 = new Coordinate(1, 3);
    Coordinate hit_point3 = new Coordinate(2, 3);
    Coordinate hit_point4 = new Coordinate(5, 6);
    s1.recordHitAt(coordi1);
    s1.recordHitAt(hit_point2);
    s1.recordHitAt(hit_point3);
    assertTrue(s1.isSunk());
    assertEquals("submarine", s1.getName());
    //assertThrows(IllegalArgumentException.class, ()->s1.)
  }


  
  @Test
  public void test_coordinate_not_in_ship(){
    Coordinate c1 = new Coordinate(8,4);
    Coordinate c2 = new Coordinate(7,5);
    Coordinate c3 = new Coordinate(0,0);
    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", c1, 2, 3, 's', '*');
    assertThrows(IllegalArgumentException.class,()->s1.checkCoordinateInThisShip(c2));
    
    // Testing for the fourth constructor
    RectangleShip<Character> s2 = new RectangleShip<Character>("submarine", c2, 's', '*');
    assertThrows(IllegalArgumentException.class,()->s1.checkCoordinateInThisShip(c3));
  }
  
  
}
