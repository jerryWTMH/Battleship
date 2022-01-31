package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
  public void test_getInfo() {
    SimpleShipDisplayInfo<Character> s1 = new SimpleShipDisplayInfo<Character>('d', 'h');
    Coordinate coordi = new Coordinate(4,4);
    assertEquals('h', s1.getInfo(coordi, true));
    assertEquals('d', s1.getInfo(coordi, false));
  }

}
