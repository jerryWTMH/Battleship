package edu.duke.ch450.battleship;

import java.util.HashSet;

public class Carrier<T> extends BasicShip<T> {
  final String name = "Carrier";
  public String getName() {
    return name;
  };

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, Character orientation){
    HashSet<Coordinate> coordis = new HashSet<Coordinate>();
    int start_r = upperLeft.getRow();
    int start_c = upperLeft.getColumn();
    if(orientation == 'U'){
      Coordinate point1 = new Coordinate(start_r, start_c);
      Coordinate point2 = new Coordinate(start_r + 1, start_c);
      Coordinate point3 = new Coordinate(start_r + 2, start_c);
      Coordinate point4 = new Coordinate(start_r + 2, start_c + 1);
      Coordinate point5 = new Coordinate(start_r + 3, start_c + 0);
      Coordinate point6 = new Coordinate(start_r + 3, start_c + 1);
      Coordinate point7 = new Coordinate(start_r + 4, start_c + 1);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);
      coordis.add(point5);
      coordis.add(point6);
      coordis.add(point7);
    }else if(orientation == 'R'){
      Coordinate point1 = new Coordinate(start_r, start_c + 1);
      Coordinate point2 = new Coordinate(start_r, start_c + 2);
      Coordinate point3 = new Coordinate(start_r, start_c + 3);
      Coordinate point4 = new Coordinate(start_r, start_c + 4);
      Coordinate point5 = new Coordinate(start_r + 1, start_c + 0);
      Coordinate point6 = new Coordinate(start_r + 1, start_c + 1);
      Coordinate point7 = new Coordinate(start_r + 1, start_c + 2);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);
      coordis.add(point5);
      coordis.add(point6);
      coordis.add(point7);
      
    } else if(orientation == 'D'){
       Coordinate point1 = new Coordinate(start_r, start_c);
      Coordinate point2 = new Coordinate(start_r + 1, start_c);
      Coordinate point3 = new Coordinate(start_r + 1, start_c + 1);
      Coordinate point4 = new Coordinate(start_r + 2, start_c + 0);
      Coordinate point5 = new Coordinate(start_r + 2, start_c + 1);
      Coordinate point6 = new Coordinate(start_r + 3, start_c + 1);
      Coordinate point7 = new Coordinate(start_r + 4, start_c + 1);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);
      coordis.add(point5);
      coordis.add(point6);
      coordis.add(point7);


    } else if(orientation == 'L'){
       Coordinate point1 = new Coordinate(start_r, start_c + 2);
      Coordinate point2 = new Coordinate(start_r, start_c + 3);
      Coordinate point3 = new Coordinate(start_r, start_c + 4);
      Coordinate point4 = new Coordinate(start_r + 1, start_c + 0);
      Coordinate point5 = new Coordinate(start_r + 1, start_c + 1);
      Coordinate point6 = new Coordinate(start_r + 1, start_c + 2);
      Coordinate point7 = new Coordinate(start_r + 1, start_c + 3);
      coordis.add(point1);
      coordis.add(point2);
      coordis.add(point3);
      coordis.add(point4);
      coordis.add(point5);
      coordis.add(point6);
      coordis.add(point7);

    }
    return coordis;
  }

  public Carrier(Placement oldPlacement, Coordinate upperLeft, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(oldPlacement, makeCoords(upperLeft, oldPlacement.getOrientation()), myDisplayInfo, enemyDisplayInfo);
    //this.name = "Battleship";
  }


  public Carrier(Placement oldPlacement, Coordinate upperLeft, T data, T onHit){
    this(oldPlacement, upperLeft, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }




  
  

}
