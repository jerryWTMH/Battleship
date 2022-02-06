package edu.duke.ch450.battleship;

import java.util.function.Function;

/**
 * This class handles textual display of a Board(i.e., converting it to a string
 * to show to the user). It supports two ways to display the Board: one for the
 * player's own board, and one for the enemy's board.
 */
public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board<Character> toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to display
   */
  public BoardTextView(Board<Character> toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }

  /**
   * Display the board, and it consist of three parts: header, middle, header
   * middle part(Width 1 : 10, Height A : Z)
   */

  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
    String header = makeHeader();
    String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
        "T", "U", "V", "W", "X", "Y", "Z" };
    String middle = "";
    for (int i = 0; i < toDisplay.getHeight(); i++) {
      StringBuilder sb = new StringBuilder();
      sb.append(alphabet[i]);
      sb.append(" ");
      for (int j = 0; j < toDisplay.getWidth(); j++) {
        Coordinate coordi = new Coordinate(i, j);
        if (getSquareFn.apply(coordi) == null) {
          sb.append(" ");
        } else {
          sb.append(getSquareFn.apply(coordi));
        }

        if (j != toDisplay.getWidth() - 1) {
          sb.append("|");
        }
      }
      sb.append(" ");
      sb.append(alphabet[i]);
      sb.append("\n");
      middle += sb.toString();
    }
    String result = header + middle + header;
    return result; // this is a placeholder for the moment
  }

   public String displayMyOwnBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForSelf(c));
  }

  public String displayEnemyBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForEnemy(c));
  }

  
  /**
   * This makes the header line, e.g. 0|1|2|3|4\n
   *
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  ");// README shows two space at
    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }

  protected String spaceGenerator(int len) {
    /*
     * if (len == 0) { return null; }
     */
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < len; i++) {
      s.append(" ");
    }
    String str = s.toString();
    return str;
  }

  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
    int width = toDisplay.getWidth();
    int bodySpace = 16;
    int bodySpace2 = 18;
    int headStart = 5;
    int headSpace = 2 * width + 22 - myHeader.length() - headStart;
    String start = spaceGenerator(headStart);
    String headS = spaceGenerator(headSpace);
    String bodyS = spaceGenerator(bodySpace);
    String bodyS2 = spaceGenerator(bodySpace2);
    // generate headline
    StringBuilder builder = new StringBuilder();
    builder = first_line_header_helper(builder,start, myHeader, headS, enemyHeader);
    // generate body
    String[] myLines = displayMyOwnBoard().split("\n");
    String[] enemyLines = enemyView.displayEnemyBoard().split("\n");
    builder = header_body_helper(builder, myLines[0], bodyS2, enemyLines[0]);
    int counter = 1;
    while (counter < myLines.length - 1) {
      builder = header_body_helper(builder, myLines[counter], bodyS, enemyLines[counter]);
      counter++;
    }
    builder = header_body_helper(builder, myLines[myLines.length - 1], bodyS2, enemyLines[myLines.length - 1]);
    return builder.toString();

  }

  public StringBuilder first_line_header_helper(StringBuilder sb, String start ,String myHeader, String headerBody, String enemyHeader){
    sb.append("\n");
    sb.append(start);
    sb.append(myHeader);
    sb.append(headerBody);
    sb.append(enemyHeader);
    sb.append("\n");
  return sb;
  }
  
  public StringBuilder header_body_helper(StringBuilder sb, String myOneLine, String headerBody, String enemyOneLine) {
    sb.append(myOneLine);
    sb.append(headerBody);
    sb.append(enemyOneLine);
    sb.append("\n");
    return sb;
  }
}
