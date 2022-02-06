package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class BattleshipTest {
  @Test
  public void test_makeCoords() {
   
    Coordinate upperLeft = new Coordinate(0,0);
    Placement oldPlacement = new Placement (upperLeft, 'U', "Battleship");
    Battleship<Character> s1 = new Battleship<Character>(oldPlacement, upperLeft, 'b', '*');
    ArrayList<Coordinate> result1 = new ArrayList<Coordinate>();
    Coordinate point1 = new Coordinate(0,1);
    Coordinate point2 = new Coordinate(1,0);
    Coordinate point3 = new Coordinate(1,1);
    Coordinate point4 = new Coordinate(1,2);
    Character orientation1 = 'U';
    result1 = s1.makeCoords(upperLeft, orientation1);
    assertEquals(true, result1.contains(point1));
    assertEquals(true, result1.contains(point2));
    assertEquals(true, result1.contains(point3));
    assertEquals(true, result1.contains(point4));
    
    Battleship<Character> s2 = new Battleship<Character>(oldPlacement, upperLeft, 'b', '*');
    ArrayList<Coordinate> result2 = new ArrayList<Coordinate>();
    point1 = new Coordinate(0,0);
    point2 = new Coordinate(1,0);
    point3 = new Coordinate(1,1);
    point4 = new Coordinate(2,0);
    Character orientation2 = 'R';
    result2 = s2.makeCoords(upperLeft, orientation2);
    assertEquals(true, result2.contains(point1));
    assertEquals(true, result2.contains(point2));
    assertEquals(true, result2.contains(point3));
    assertEquals(true, result2.contains(point4));

     Battleship<Character> s3 = new Battleship<Character>(oldPlacement, upperLeft, 'b', '*');
     ArrayList<Coordinate> result3 = new ArrayList<Coordinate>();
    point1 = new Coordinate(0,0);
    point2 = new Coordinate(0,1);
    point3 = new Coordinate(0,2);
    point4 = new Coordinate(1,1);
    Character orientation3 = 'D';
    result3 = s3.makeCoords(upperLeft, orientation3);
    assertEquals(true, result3.contains(point1));
    assertEquals(true, result3.contains(point2));
    assertEquals(true, result3.contains(point3));
    assertEquals(true, result3.contains(point4));

    Battleship<Character> s4 = new Battleship<Character>(oldPlacement, upperLeft, 'b', '*');
    ArrayList<Coordinate> result4 = new ArrayList<Coordinate>();
    point1 = new Coordinate(0,1);
    point2 = new Coordinate(1,0);
    point3 = new Coordinate(1,1);
    point4 = new Coordinate(2,1);
    Character orientation4 = 'L';
    result4 = s4.makeCoords(upperLeft, orientation4);
    assertEquals(true, result4.contains(point1));
    assertEquals(true, result4.contains(point2));
    assertEquals(true, result4.contains(point3));
    assertEquals(true, result4.contains(point4));
    
  }

  @Test
  public void test_getName(){
    Placement oldPlacement = new Placement(new Coordinate(0,0),'H');
    Battleship<Character> ship = new Battleship<Character>(oldPlacement, new Coordinate(3,1), 'b', '*');
    assertEquals(ship.getName(), "Battleship");
  }

}
