package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_checkMyRule() {
    V1ShipFactory factory = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1,2), 'V');
    Ship<Character> s = factory.makeSubmarine(v1_2);
    Ship<Character> b = factory.makeBattleship(v1_2);
    Placement h1_2 = new Placement(new Coordinate(1,2), 'H');
    Ship<Character> c = factory.makeCarrier(h1_2);
    BattleShipBoard<Character> board = new BattleShipBoard<Character>(4,4,'X');
    PlacementRuleChecker<Character> i = new InBoundsRuleChecker<Character>(null);
    assertEquals(null, i.checkMyRule(s, board));
    //assertEquals("1", i.checkPlacement(s, board));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.", i.checkMyRule(b, board));
     assertEquals("That placement is invalid: the ship goes off the right of the board.", i.checkMyRule(c, board));

    Placement top_off = new Placement(new Coordinate(-1, 2), 'H');
    Ship<Character> d = factory.makeDestroyer(top_off);
    assertEquals("That placement is invalid: the ship goes off the top of the board.", i.checkPlacement(d, board));

    Placement left_off = new Placement(new Coordinate(2, -1), 'V');
    Ship<Character> s2 = factory.makeDestroyer(left_off);
    assertEquals("That placement is invalid: the ship goes off the left of the board.", i.checkPlacement(s2, board));
  }

}
