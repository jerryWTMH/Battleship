package edu.duke.ch450.battleship;
/**
 * This is the Placement java
 * This will return Coordinate and orientation
*/
public class Placement {
  private final Coordinate where;
  private final char orientation;


  public Coordinate getWhere(){
    return this.where;
  }

  public char getOrientation(){
    return Character.toUpperCase(this.orientation);
  }
  
  public Placement(Coordinate coordi, char ori){
    this.where = coordi;
    this.orientation = Character.toUpperCase(ori);
  }

  public Placement(String str){
    Coordinate coordi = new Coordinate(str.substring(0,2));
    char c = str.charAt(2);
    this.where = coordi;
    this.orientation = c;
  }

  @Override
  public boolean equals(Object o) {
  
    if (o.getClass().equals(getClass())) {
      Placement p = (Placement) o; // cast object o into the Placement
      return where.equals(p.where) && orientation == p.orientation;
      
    }
    return false; 
  }
  
  @Override
  public int hashCode(){
    return toString().hashCode();
  }

  @Override
  public String toString(){
    return where.toString()+orientation;
  }

}
