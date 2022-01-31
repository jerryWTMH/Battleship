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
    BattleShipBoard<Character> board = new BattleShipBoard<Character>(3,3);
    PlacementRuleChecker<Character> i = new InBoundsRuleChecker<Character>(null);
    assertEquals(true, i.checkMyRule(s, board));
    assertEquals(true, i.checkPlacement(s, board));
    assertEquals(false, i.checkMyRule(b, board));
    assertEquals(false, i.checkPlacement(b, board));
  }

}
