package edu.duke.ch450.battleship;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This is the Coordinate class to record the row and column On top of that,
 * there are some functions to help user to get more information about the
 * coordinate on the BattleShipBoard
 */
public class Coordinate {
  private final int row;
  private final int column;
  public int getRow() {
    return this.row;
  }

  public int getColumn() {
    return this.column;
  }

  public Coordinate(int r, int c) {
    this.row = r;
    this.column = c;
  }

  /**
   * check whether the class of o is the same as the class of the class of
   * Coordinate
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o; // cast object o into the Coordinate
      return row == c.row && column == c.column;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    return "(" + this.row + "," + this.column + ")";
  }

  public Coordinate(String descr) {
    descr = descr.toUpperCase();
    char ch = descr.charAt(0);
    if (descr.length() != 2) {
      throw new IllegalArgumentException("The length should not be over 2!\n");
    }
    if ((ch - 'A') < 0 || (ch - 'A') > 25) {
      throw new IllegalArgumentException("The first character should be English alphabet!\n");
    }
    int row_number = 0;
    for (int i = 1; i < descr.length(); i++) {
      int new_number = descr.charAt(i) - '0';
      if (new_number < 0 || new_number > 9) {
        throw new IllegalArgumentException("Please enter the number!\n");
      }
      row_number = 10 * row_number + new_number;
    }
    this.row = ch - 'A';
    this.column = row_number;
  }

  /*
   * boundCheck will help us to check whether the coordinate locates inside of the
   * Board. true represents not out of the bound. false represents out of the
   * bound
   * 
   * @param w is the Board's width
   * 
   * @param h is the Board's height
   */
  public boolean boundCheck(int w, int h) {
    if (row >= h || column >= w) {
      return false;
    }
    return true;
  }

  public HashMap<String, Integer> sonaCheck(Board<Character> enemyBoard) {
    HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
    resultMap.put("Submarine",0);
    resultMap.put("Destroyer",0);
    resultMap.put("Battleship",0);
    resultMap.put("Carrier",0);
    int r = this.row;
    int c = this.column;
    int r_min = (r - 3) <= 0 ? 0 : (r - 3);
    int c_min = (c - 3) <= 0 ? 0 : (c - 3);
    int r_max = (r + 3) >= enemyBoard.getHeight() ? enemyBoard.getHeight() - 1 : (r + 3);
    int c_max = (c + 3) >= enemyBoard.getWidth() ? enemyBoard.getWidth() - 1 : (c + 3);
    HashSet<Coordinate> sonaSet = new HashSet<Coordinate>();
    
    for (int i = r_min; i <= r_max; i++) {
      for (int j = c_min; j <= c_max; j++) {
        if ((Math.abs(i - r) + Math.abs(j - c)) <= 4) {
          Coordinate coordi = new Coordinate(i, j);
          sonaSet.add(coordi);
        }
      }
    }
    return enemyBoard.collectSona(sonaSet,resultMap);
  }

}
