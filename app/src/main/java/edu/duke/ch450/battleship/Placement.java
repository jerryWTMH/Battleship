package edu.duke.ch450.battleship;
/**
 * This is the Placement java
 * This will return Coordinate and orientation
*/
public class Placement {
  private final Coordinate where;
  private final char orientation;
  /**
   *Get the Coordinate where
*/

  public Coordinate getWhere(){
    return this.where;
  }
  /**
   *Get the char orientation
*/
  public char getOrientation(){
    return Character.toUpperCase(this.orientation);
  }

  /**
   * Consructs a placement, given the coordinate and char. 
   * @param coordi is the coordinate
   * @param ori is the orientation char

*/
  
  public Placement(Coordinate coordi, char ori){
    this.where = coordi;
    ori  = Character.toUpperCase(ori);
    if(ori != 'H' && ori != 'V'){
      throw new IllegalArgumentException("Invalid orientation input");
    }
    this.orientation = ori;
  }

  /**
   * Consructs a placement, given the string of valid format(e.g.,"A2D").
   * 
   * @param str is the string of placement info
   * @throws Illegalargumentexception if the string length is not 3 because of the constructor of Coordinate.
   */

  public Placement(String str){
    str = str.toUpperCase();
    if(str.length() != 3){
      throw new IllegalArgumentException("Invalid input length");
    }
    if(str.charAt(2) != 'H' && str.charAt(2) != 'V'){
      throw new IllegalArgumentException("Invalid orientaion input");
    }
    Coordinate coordi = new Coordinate(str.substring(0,2));
    char c = str.charAt(2);
    this.where = coordi;
    this.orientation = c;
  }

  /**
   * This check if an object is equal to the placement.
   * 
   * @param o is the object to check
   * @return the boolean result of equal check
   */

  @Override
  public boolean equals(Object o) {
  
    if (o.getClass().equals(getClass())) {
      Placement p = (Placement) o; // cast object o into the Placement
      return where.equals(p.where) && orientation == p.orientation;
      
    }
    return false; 
  }
  
  /**
   * This converts placement.toString() to hashcode.
   * 
   * @return the resulted placement hashcode 
   */
  
  @Override
  public int hashCode(){
    return toString().hashCode();
  }

   /**
   * This combines and converts coordinate and char orientation to string.
   *
   * @return the string combined
   */
  @Override
  public String toString(){
    return where.toString()+orientation;
  }

}
