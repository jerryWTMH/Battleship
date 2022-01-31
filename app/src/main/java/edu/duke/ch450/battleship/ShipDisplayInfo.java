package edu.duke.ch450.battleship;

public interface ShipDisplayInfo<T> {
  public T getInfo(Coordinate where, boolean hit);
}
