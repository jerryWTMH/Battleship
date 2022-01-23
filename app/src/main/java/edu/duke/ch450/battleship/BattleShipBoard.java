package edu.duke.ch450.battleship;

public class BattleShipBoard implements Board{
  private final int width;
  private final int height;
  public int getWidth(){
    return this.width;
  };  
  public int getHeight(){
    return this.height;
  };
  public BattleShipBoard(int w, int h){
    /**
     * Constructs a BattleShipBoard with the specified width and height
     *  @param w is the width  of the newly constructed board.
     * @param h is the height of the newly constructed board.
     * @throws IIllegalArgumentException if the width or height are less than or equal to zero.
     */
    if(w <= 0){
      throw new IllegalArgumentException("BattleShipBoard's width must be positivie but is " + w);
    }
    if(h <= 0){
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
      
    }
    this.width = w;
    this.height = h;
  }
}
