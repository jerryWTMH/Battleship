package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  @Test
  public void test_getter() {
    Coordinate coordi = new Coordinate(1,2);
    Coordinate coordi2 = new Coordinate(1,2);
    Placement p = new Placement(coordi, 'V');
    assertEquals(p.getOrientation(), 'V');
    assertEquals(p.getWhere(), coordi2);
  }
  @Test
  public void test_toString(){
    Placement p = new Placement("A2D");
    assertEquals("(0,2)D",p.toString());
  }
  @Test
  public void test_equals(){
    Placement p1 = new Placement("A2D");
    Placement p2 = new Placement("A2D");
    Placement p3 = new Placement("B3E");
    Placement p4 = new Placement("B3D");
    assertEquals(p1, p2);
    assertEquals(p1, p1);
    assertNotEquals(p1, p3);
    assertNotEquals(p1, p4);
    assertNotEquals(p4, "B3");
  }

  @Test
  public void test_hashCode(){
    Placement p1 = new Placement("A2D");
    Placement p2 = new Placement("A2D");
    Placement p3 = new Placement("A1C");
    Placement p4 = new Placement("A2H");
    assertEquals(p1.hashCode(),p2.hashCode());
    assertNotEquals(p2.hashCode(),p3.hashCode());
    assertNotEquals(p2.hashCode(),p4.hashCode());
    assertNotEquals(p4.hashCode(),p3.hashCode());

  }
 }
