package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_no_collision() {
    BattleShipBoard<Character> board = new BattleShipBoard<Character>(8,8);
    V1ShipFactory factory = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1,2), 'V');
    Ship<Character> c = factory.makeCarrier(v1_2);
    PlacementRuleChecker<Character> i = new NoCollisionRuleChecker<Character>(null);
    assertEquals(true, i.checkMyRule(c, board));
    board.tryAddShip(c);
    Ship<Character> b = factory.makeBattleship(v1_2);
    assertEquals(false, i.checkMyRule(b, board));
  }

  @Test
  public void test_combine_rules(){
    PlacementRuleChecker<Character> i1 = new NoCollisionRuleChecker<Character>(null);
    PlacementRuleChecker<Character> i2 = new InBoundsRuleChecker<Character>(i1);
    BattleShipBoard<Character> board = new BattleShipBoard<Character>(9,9);
    V1ShipFactory factory = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1,2), 'V');
    Ship<Character> c = factory.makeCarrier(v1_2);
    assertEquals(true, i2.checkPlacement(c, board));

    board.tryAddShip(c);
    Placement h1_2 = new Placement(new Coordinate(2,2), 'H');
    Ship<Character> s = factory.makeSubmarine(h1_2);
    assertNotEquals(true, i2.checkPlacement(s, board));

    Placement h7_7 = new Placement(new Coordinate(7,7), 'V');
    Ship<Character> b = factory.makeBattleship(h7_7);
    assertEquals(false, i2.checkPlacement(b, board));
  }

}
