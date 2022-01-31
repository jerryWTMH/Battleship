package edu.duke.ch450.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
  public T myData;
  public T onHit;
  @Override
  public T getInfo(Coordinate where, boolean hit) {
    // TODO Auto-generated method stub
    if(hit){
      return onHit;
    } else{
      return myData;
    }
  }

  public SimpleShipDisplayInfo(T data, T hit){
    this.myData = data;
    this.onHit = hit;
  }
  
}
