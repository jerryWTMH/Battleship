package edu.duke.ch450.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
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
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V1ShipFactory factory = new V1ShipFactory();
    V2ShipFactory factory2 = new V2ShipFactory();
    return new TextPlayer(board, input, output, factory,factory2, "A");
  }
  
  @Test
  void test_read_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2U\nC8L\na4U\n", bytes);
    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'U', "Battleship");
    expected[1] = new Placement(new Coordinate(2, 8), 'L', "Battleship");
    expected[2] = new Placement(new Coordinate(0, 4), 'U',"Battleship");
    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt, "Battleship");
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

  
  /*@Test
  public void test_doOnePlacement_null(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);
    String prompt = "checkcheckcheck\ncheckehcfjls";
    assertThrows(EOFException.class, ()->player.readPlacement(prompt));  
  }*/


  @Test
  void test_read_coordinate() throws IOException{
    //Test IOException
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer playerIOE = createTextPlayer(4, 4, "", bytes);
    assertThrows(IOException.class,()->playerIOE.readCoordinate());
    bytes.reset();
    //Test wrong format coordinate
    TextPlayer playerFormat = createTextPlayer(4, 4, "%3\na3\n", bytes);
    assertEquals(0, playerFormat.readCoordinate().getRow());
    bytes.reset();
    //Test out of bound
    TextPlayer playerBound = createTextPlayer(4, 25, "Z3\ng3\n", bytes);
    playerBound.readCoordinate();
    String expected = "Please enter the fire location:\n"+
      "The coordinate is out of the bound!\n"+
        "Please enter the fire location:\n";
    assertEquals(expected, bytes.toString());
  }

  /*@Test
  void test_play_one_turn() throws IOException{
     Board<Character> b = new BattleShipBoard(4,3,'X');
     V1ShipFactory f = new V1ShipFactory();
     Ship<Character> s = f.makeDestroyer(new Placement("B1h"));
     b.tryAddShip(s);
     ByteArrayOutputStream bytes = new ByteArrayOutputStream();
     TextPlayer player = createTextPlayer(4, 3, "B2\nc2\nb1\n", bytes);
     player.playOneTurn(b, "B");
     player.playOneTurn(b, "B");
     bytes.reset();
     player.playOneTurn(b, "B");
     String expected = "Player A's turn:\n\n"+
         "     Your ocean               Player B's ocean:\n"+
          "  0|1|2|3                    0|1|2|3\n"+
   "A  | | |  A                A  | | |  A\n"+
   "B  | | |  B                B  | |d|  B\n"+
   "C  | | |  C                C  | |X|  C\n"+
        "  0|1|2|3                    0|1|2|3\n"+
       "\nPlease enter the fire location:\n"+
         "You hit a Destroyer!\n";
       
     assertEquals(expected, bytes.toString());
     }*/

  @Test
  public void test_check_allSunk(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BattleShipBoard<Character> board = new BattleShipBoard(4,3,'X');
     V1ShipFactory f = new V1ShipFactory();
     Ship<Character> s = f.makeSubmarine(new Placement("B0h"));
     board.tryAddShip(s);
     s.wasHitAt(new Coordinate(1,0));
     s.wasHitAt(new Coordinate(1,1));
     TextPlayer player = createTextPlayer(4, 3, "B2\nc2\nb1\n", bytes);
     assertEquals(true, player.checkLose());
  }
  
  /*@Test
  public void test_doPlacementPhase(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(4,4)
  }*/
}
