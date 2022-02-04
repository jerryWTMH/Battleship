package edu.duke.ch450.battleship;

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
   *check whether the class of o is the same as the class of the class of Coordinate
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
  public int hashCode(){
    return toString().hashCode();
  }

  @Override
  public String toString(){
    return "(" + this.row + "," +  this.column + ")";
  }

  public Coordinate(String descr){
    descr = descr.toUpperCase();
    char ch = descr.charAt(0);
    if (descr.length() != 2){ 
      throw new IllegalArgumentException("The length should not be over 2!\n");
    }
    if((ch - 'A') < 0 || (ch - 'A') > 25){
      throw new IllegalArgumentException("The first character should be English alphabet!\n");
    }
    int row_number = 0; 
    for(int i = 1; i < descr.length(); i++){
      int new_number = descr.charAt(i) - '0';
      if(new_number < 0 || new_number > 9){
         throw new IllegalArgumentException("Please enter the number!\n");
      }
      row_number = 10 * row_number + new_number;
    }
    this.row = ch - 'A';
    this.column = row_number;
  }

  /*
   * boundCheck will help us to check whether the coordinate locates inside of the Board.
   * true represents not out of the bound. false represents out of the bound
   * @param w is the Board's width
   * @param h is the Board's height
*/
  public boolean boundCheck(int w, int h){
    if(row >= h || column >= w){
      return false;
    }
    return true;
  }

  
  
}
