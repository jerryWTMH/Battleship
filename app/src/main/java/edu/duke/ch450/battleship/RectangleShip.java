package edu.duke.ch450.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){
    HashSet<Coordinate> coordis = new HashSet<Coordinate>();
    int start_x = upperLeft.getRow();
    int start_y = upperLeft.getColumn();
    for(int r = start_x; r < start_x + height ; r++){
      for(int c = start_y; c < start_y + width; c++){
        Coordinate point = new Coordinate(r, c);
        coordis.add(point);
      }
    }
    return coordis;
  }

  public RectangleShip(Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> a){
    super(makeCoords(upperLeft, width, height), a);
  }

  public RectangleShip(Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this(upperLeft, 1, 1, data, onHit);
  }

  

  

 
  
}
