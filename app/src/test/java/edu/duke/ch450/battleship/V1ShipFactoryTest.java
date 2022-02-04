package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {
  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter,
      Coordinate... expectedLocs) {
    if (testShip.getName() != expectedName) {
      throw new IllegalArgumentException("The name of the ship is wrong!");
    }
    for (Coordinate coordi : expectedLocs) {
      if (testShip.occupiesCoordinates(coordi) == false) {
        throw new IllegalArgumentException("The location is not in the ship! ");
      }

      if (testShip.getDisplayInfoAt(coordi,true) != expectedLetter) {
        throw new IllegalArgumentException("The information at the location is not match with the expectedLetter1");
      }
    }
  }

  @Test
  public void test_v1_ship_factory() {
    V1ShipFactory f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'V');
    /**
     * Test Destroyer First throw: Wrong Name. Second throw: Wrong Symbol. Third throw:
     * Wrong location, and it is not in any part of the ship.
     */
    Ship<Character> destroy = f.makeDestroyer(v1_2);
    assertThrows(IllegalArgumentException.class,
        () -> checkShip(destroy, "Destroye", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2)));
    assertThrows(IllegalArgumentException.class,
        () -> checkShip(destroy, "Destroyer", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2)));
    assertThrows(IllegalArgumentException.class, () -> checkShip(destroy, "Destroyer", 'd', new Coordinate(1, 2),
        new Coordinate(2, 2), new Coordinate(3, 2), new Coordinate(5, 5)));

    Ship<Character> submarine = f.makeSubmarine(v1_2);
    assertThrows(IllegalArgumentException.class,
        () -> checkShip(submarine, "Submarine", 'd', new Coordinate(1, 2), new Coordinate(2, 2)));

    Ship<Character> battle = f.makeBattleship(v1_2);
    assertThrows(IllegalArgumentException.class,
        () -> checkShip(battle, "BattleShip", 'b', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2)));

    Ship<Character> carrier = f.makeCarrier(v1_2);
    assertThrows(IllegalArgumentException.class,
        () -> checkShip(carrier, "Carriera", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2)));

    Placement h1_2 = new Placement(new Coordinate(1, 2), 'H');
    Ship<Character> sub1 = f.makeSubmarine(h1_2);
    assertThrows(IllegalArgumentException.class,
        () -> checkShip(sub1, "Submarine", 'd', new Coordinate(1, 2), new Coordinate(1, 3)));

  }

}
