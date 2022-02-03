package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  /**
   * This is the helper function to help you to create a TextPlayer
*/
  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer(board, input, output, shipFactory, "A");
  }
  
  @Test
  void test_read_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');
    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(p, expected[i]); // did we get the right Placement back
      assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }


  /**
   * test_do_one_replacement() add one ship into our board, and test whether it is correct ornot with the
   * function of doOnePlacement() in App.java
   */
  
  
  @Test
  void test_do_one_replacement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(5, 5, "B1H\n", bytes);
    String expectedHeader = "  0|1|2|3|4\n";
    String expectedBody = "A  | | | |  A\n" + "B  |d|d|d|  B\n" + "C  | | | |  C\n" + "D  | | | |  D\n" + "E  | | | |  E\n";
    String expected = "Player " + player.name + " " + "where do you want to place a Destroyer?\n" + expectedHeader + expectedBody + expectedHeader;
    V1ShipFactory factory = new V1ShipFactory();
    player.doOnePlacement("Destroyer", (p)-> factory.makeDestroyer(p));
    assertEquals(expected, bytes.toString());
  }

  /*@Test
  void test_setupShipCreationMap(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(5, 5, "B1H\n", bytes);
    player.setupShipCreationMap();
    for(String key: player.shipCreationFns.keySet()){
      if(key == "Submarine"){
        V1ShipFactory factory = new V1ShipFactory();
        Ship<Character> s1 = factory.makeSubmarine(new Placement("B1H"));
        assertEquals("Submarine", s1.getName());
        Ship<Character> s2 = player.shipCreationFns.get(key);
      }
      
    }
  }*/

}
