
package edu.duke.ch450.battleship;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    String expectedHeader= "  0|1\n";
    String Body=
      "A  |  A\n"+
      "B  |  B\n";
    emptyBoardHelper(2,2,expectedHeader, Body);
    
  }
  @Test
   public void test_display_empty_3by2() {
     String expectedHeader= "  0|1|2\n";
    String Body=
      "A  | |  A\n"+
      "B  | |  B\n";
    emptyBoardHelper(3,2,expectedHeader, Body);
  }
  @Test
   public void test_display_empty_5by2() {
    String expectedHeader= "  0|1|2|3|4\n";
    String Body=
      "A  | | | |  A\n"+
      "B  | | | |  B\n";
      emptyBoardHelper(5,2,expectedHeader, Body);
  }

  /**
   * This is the board which is for the empty board.
   */
   private void emptyBoardHelper(int w, int h, String expectedHeader, String expectedBody){
     Board<Character> b1 = new BattleShipBoard<Character>(w, h,'X');
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  
  /**
   * This is the helper function for the board which is not empty
   */
  private void BoardHelper(int w, int h, String expectedHeader, String expectedBody, Board<Character> b1){
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  

  @Test
  public void test_invalid_board_size(){
    Board<Character> wideBoard = new BattleShipBoard<Character>(11,20,'X');
    Board<Character> tailBoard = new BattleShipBoard<Character>(10,27,'X');
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tailBoard));
  }
  /**
   * Test whether the BoardTextView could display normally in the empty board or board that has some ships.
*/
  @Test
  public void test_ship_display(){
    // Empty Ship now!
    Board<Character> b1 = new BattleShipBoard<Character>(4,4,'X');    
    String expectedHeader= "  0|1|2|3\n";
    String Body=
      "A  | | |  A\n"+
      "B  | | |  B\n"+
      "C  | | |  C\n"+
      "D  | | |  D\n";
    emptyBoardHelper(4,4,expectedHeader, Body);

    // Adding ship now!
    Coordinate c1 = new Coordinate(2,3);
    RectangleShip<Character> s1 = new RectangleShip<Character>(c1, 's', '*');
    b1.tryAddShip(s1);
    String Body2=
      "A  | | |  A\n"+
      "B  | | |  B\n"+
      "C  | | |s C\n"+
      "D  | | |  D\n";
    BoardHelper(4,4,expectedHeader, Body2, b1);
    // Adding one more ship!
    Coordinate c2 = new Coordinate(0,0);
    RectangleShip<Character> s2 = new RectangleShip<Character>(c2, 's', '*');
    b1.tryAddShip(s2);
    String Body3=
      "A s| | |  A\n"+
      "B  | | |  B\n"+
      "C  | | |s C\n"+
      "D  | | |  D\n";
    BoardHelper(4,4,expectedHeader, Body3, b1);
      
  }

  
  @Test
  public void test_display_nonEmpty_3by2(){
    String expectedHeader = "  0|1|2\n";
    String expectedBody =
      "A  | |s A\n"+
      "B  |s|  B\n";
    String expected = expectedHeader+expectedBody+expectedHeader;
    Board<Character> b = new BattleShipBoard(3,2,'X');
    Coordinate c1 = new Coordinate(0,2);
    Coordinate c2 = new Coordinate(1,1);
    RectangleShip<Character> sh1 = new RectangleShip<Character>(c1, 's', '*');
    RectangleShip<Character> sh2 = new RectangleShip<Character>(c2, 's', '*');
    b.tryAddShip(sh1);
    b.tryAddShip(sh2);
    BoardTextView view = new BoardTextView(b);
    assertEquals(expected,view.displayMyOwnBoard());
  }

  @Test
  public void test_display_enemy_3by2(){
    String myView =
      "  0|1|2|3\n" +
      "A  | | |d A\n" +
      "B s|*| |d B\n" +
      "C  | | |d C\n" +
      "  0|1|2|3\n";
      String enemyView =
      "  0|1|2|3\n" +
      "A  | | |  A\n" +
      "B  |s| |  B\n" +
      "C X| | |  C\n" +
      "  0|1|2|3\n";
    Board<Character> b = new BattleShipBoard(4,3,'X');
    Placement p1 = new Placement("B0H");
    Placement p2 = new Placement("A3V");
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s = f.makeSubmarine(p1);
    Ship<Character> d = f.makeDestroyer(p2);
    b.tryAddShip(s);
    b.tryAddShip(d);
    BoardTextView view = new BoardTextView(b);
    Coordinate fire = new Coordinate(1,1);
    Coordinate miss = new Coordinate(2,0);
    b.fireAt(fire);
    b.fireAt(miss);
    assertEquals(myView,view.displayMyOwnBoard());
    assertEquals(enemyView, view.displayEnemyBoard()); 
    
  }

}
