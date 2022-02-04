package edu.duke.ch450.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
  final String name;

  public String getName() {
    return name;
  };

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
    HashSet<Coordinate> coordis = new HashSet<Coordinate>();
    int start_x = upperLeft.getRow();
    int start_y = upperLeft.getColumn();
    for (int r = start_x; r < start_x + height; r++) {
      for (int c = start_y; c < start_y + width; c++) {
        Coordinate point = new Coordinate(r, c);
        coordis.add(point);
      }
    }
    return coordis;
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, data, onHit);
    }

   public RectangleShip(String name, Coordinate upperLeft, T data, T onHit){
    this("testship", upperLeft, 1, 1, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }

}
