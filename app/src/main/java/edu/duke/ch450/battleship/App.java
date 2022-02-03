/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ch450.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public class App {
  private TextPlayer player1;
  private TextPlayer player2;

  public App(TextPlayer player1, TextPlayer player2){
    this.player1 = player1;
    this.player2 = player2;
  }
  
  /*public App(Board<Character> theBoard, Reader inputSource, PrintStream out) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = new BufferedReader(inputSource);
    this.out = out;    
    }*/

  /**
   * It will call the doPlacementPhase of each players(in TextPlayer.java)
   * And then print out the related information on the command line
   */
  public void doPlacementPhase() throws IOException{
    this.player1.doPlacementPhase();
    this.player2.doPlacementPhase();
  }

  
  public static void main(String[] args) throws IOException {
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10, 10);
    BattleShipBoard<Character> b2 = new BattleShipBoard<Character>(10, 10);
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);
    PrintStream out = System.out;
    V1ShipFactory factory = new V1ShipFactory();
    TextPlayer player1 = new TextPlayer(b1,br,out,factory,"A");
    TextPlayer player2 = new TextPlayer(b2,br,out,factory,"B");
    App app = new App(player1, player2);
    //app.doOnePlacement();
    app.doPlacementPhase();
  }
}
