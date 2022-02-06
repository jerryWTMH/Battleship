package edu.duke.ch450.battleship;

import java.util.ArrayList;
import java.util.HashSet;

public class Battleship<T> extends BasicShip<T> {
  final String name = "Battleship";
  public String getName() {
    return name;
  };

  static ArrayList<Coordinate> makeCoords(Coordinate upperLeft, Character orientation){
    ArrayList<Coordinate> coordis = new ArrayList<Coordinate>();
    int start_r = upperLeft.getRow();
    int start_c = upperLeft.getColumn();
    if(orientation == 'U'){
      Coordinate point1 = new Coordinate(start_r, start_c + 1);
      Coordinate point2 = new Coordinate(start_r + 1, start_c);
      Coordinate point3 = new Coordinate(start_r + 1, start_c + 1);
      Coordinate point4 = new Coordinate(start_r + 1, start_c + 2);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);
    }else if(orientation == 'R'){
      Coordinate point2 = new Coordinate(start_r, start_c);
      Coordinate point3 = new Coordinate(start_r + 1, start_c);
      Coordinate point1 = new Coordinate(start_r + 1, start_c + 1);
      Coordinate point4 = new Coordinate(start_r + 2, start_c);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);
      
    } else if(orientation == 'D'){
      Coordinate point4 = new Coordinate(start_r, start_c);
      Coordinate point3 = new Coordinate(start_r, start_c + 1);
      Coordinate point2 = new Coordinate(start_r, start_c + 2);
      Coordinate point1 = new Coordinate(start_r + 1, start_c + 1);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);

    } else if(orientation == 'L'){
      Coordinate point4 = new Coordinate(start_r, start_c + 1);
      Coordinate point1 = new Coordinate(start_r + 1, start_c);
      Coordinate point3 = new Coordinate(start_r + 1, start_c + 1);
      Coordinate point2 = new Coordinate(start_r + 2, start_c + 1);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);
    }
    return coordis;
  }

  public Battleship(Placement oldPlacement, Coordinate upperLeft, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(oldPlacement, makeCoords(upperLeft, oldPlacement.getOrientation()), myDisplayInfo, enemyDisplayInfo);
    //this.name = "Battleship";
  }

  /* public Battleship(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
    }


   public Battleship(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
     this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }*/

  /*public Battleship(Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, data, onHit);
    }*/

  public Battleship(Placement oldPlacement, Coordinate upperLeft, T data, T onHit){
    this(oldPlacement, upperLeft, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }




  
  

}
