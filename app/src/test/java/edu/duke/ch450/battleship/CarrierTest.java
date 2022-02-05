package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class CarrierTest {
  @Test
  public void test_makeCoords() {
   
    Coordinate upperLeft = new Coordinate(0,0);
    Placement oldPlacement = new Placement (upperLeft, 'U', "Carrier");
    Carrier<Character> s1 = new Carrier<Character>(oldPlacement, upperLeft, 'c', '*');
    HashSet<Coordinate> result1 = new HashSet<Coordinate>();
    Coordinate point1 = new Coordinate(0,0);
    Coordinate point2 = new Coordinate(1,0);
    Coordinate point3 = new Coordinate(2,0);
    Coordinate point4 = new Coordinate(2,1);
    Coordinate point5 = new Coordinate(3,0);
    Coordinate point6 = new Coordinate(3,1);
    Coordinate point7 = new Coordinate(4,1);
    Character orientation1 = 'U';
    result1 = s1.makeCoords(upperLeft, orientation1);
    assertEquals(true, result1.contains(point1));
    assertEquals(true, result1.contains(point2));
    assertEquals(true, result1.contains(point3));
    assertEquals(true, result1.contains(point4));
    assertEquals(true, result1.contains(point5));
    assertEquals(true, result1.contains(point6));
    assertEquals(true, result1.contains(point7));

    Carrier<Character> s2 = new Carrier<Character>(oldPlacement, upperLeft, 'c', '*');
    HashSet<Coordinate> result2 = new HashSet<Coordinate>();
    point1 = new Coordinate(0,1);
    point2 = new Coordinate(0,2);
    point3 = new Coordinate(0,3);
    point4 = new Coordinate(0,4);
    point5 = new Coordinate(1,0);
    point6 = new Coordinate(1,1);
    point7 = new Coordinate(1,2);
    Character orientation2 = 'R';
    result2 = s2.makeCoords(upperLeft, orientation2);
    assertEquals(true, result2.contains(point1));
    assertEquals(true, result2.contains(point2));
    assertEquals(true, result2.contains(point3));
    assertEquals(true, result2.contains(point4));
    assertEquals(true, result2.contains(point5));
    assertEquals(true, result2.contains(point6));
    assertEquals(true, result2.contains(point7));

    Carrier<Character> s3 = new Carrier<Character>(oldPlacement, upperLeft, 'c', '*');
    HashSet<Coordinate> result3 = new HashSet<Coordinate>();
    point1 = new Coordinate(0,0);
    point2 = new Coordinate(1,0);
    point3 = new Coordinate(1,1);
    point4 = new Coordinate(2,0);
    point5 = new Coordinate(2,1);
    point6 = new Coordinate(3,1);
    point7 = new Coordinate(4,1);
    Character orientation3 = 'D';
    result3 = s3.makeCoords(upperLeft, orientation3);
    assertEquals(true, result3.contains(point1));
    assertEquals(true, result3.contains(point2));
    assertEquals(true, result3.contains(point3));
    assertEquals(true, result3.contains(point4));
    assertEquals(true, result3.contains(point5));
    assertEquals(true, result3.contains(point6));
    assertEquals(true, result3.contains(point7));
    
    Carrier<Character> s4 = new Carrier<Character>(oldPlacement, upperLeft, 'c', '*');
    HashSet<Coordinate> result4 = new HashSet<Coordinate>();
    point1 = new Coordinate(0,2);
    point2 = new Coordinate(0,3);
    point3 = new Coordinate(0,4);
    point4 = new Coordinate(1,0);
    point5 = new Coordinate(1,1);
    point6 = new Coordinate(1,2);
    point7 = new Coordinate(1,3);
    Character orientation4 = 'L';
    result4 = s4.makeCoords(upperLeft, orientation4);
    assertEquals(true, result4.contains(point1));
    assertEquals(true, result4.contains(point2));
    assertEquals(true, result4.contains(point3));
    assertEquals(true, result4.contains(point4));
    assertEquals(true, result4.contains(point5));
    assertEquals(true, result4.contains(point6));
    assertEquals(true, result4.contains(point7));
  }

   @Test
  public void test_getName(){
    Placement oldPlacement = new Placement(new Coordinate(0,0),'H');
    Carrier<Character> ship = new Carrier<Character>(oldPlacement, new Coordinate(3,1), 'c', '*');
    assertEquals(ship.getName(), "Carrier");
  }

}
