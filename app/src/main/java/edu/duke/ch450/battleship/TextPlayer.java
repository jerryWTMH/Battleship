package edu.duke.ch450.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;
import java.io.EOFException;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final V1ShipFactory factory;
  String name;
  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
  
  protected void setupShipCreationMap(){
    shipCreationFns.put("Submarine", (p)->factory.makeSubmarine(p));
    shipCreationFns.put("Destroyer", (p)->factory.makeDestroyer(p));
    shipCreationFns.put("Battleship", (p)->factory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p)->factory.makeCarrier(p));
  }
  protected void setupShipCreationList(){
    shipsToPlace.addAll(Collections.nCopies(1, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(1, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(1, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(1, "Carrier"));
  }

  public TextPlayer(Board<Character> theBoard, BufferedReader inputReader, PrintStream out, V1ShipFactory factory,
      String name) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputReader;
    this.out = out;
    this.factory = factory;
    this.name = name;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
  }

  /**
   * readPlacement will print out the prompt in the first time And then ask and
   * collect for the user about the Placement that they wanna put the ship return
   * the Placement!
   */
  public Placement readPlacement(String prompt) throws IOException, EOFException {
    out.println(prompt);
    String s = inputReader.readLine();
    if(s == null){
      throw new EOFException("The input is Empty!");
    }
    return new Placement(s);
  }

  /**
   * doOnePlacement() will print out the prompt And then create a placement and
   * coordinate for the ship and then add the ship into the board
   */

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
  }
  

  /**
   * doPlacementPhase() will printout an empty board first And then print out the
   * prompt And then call the doOnePlacement()
   */
  public void doPlacementPhase() throws IOException {
    // TASK 16 LINE: 230 - 244
    out.println(view.displayMyOwnBoard());
    String prompt = "Player " + name + ":"
        + " you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
        + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
        + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n";
    out.println(prompt);
    setupShipCreationMap();
    setupShipCreationList();
    for(String s : shipsToPlace){
      doOnePlacement(s, shipCreationFns.get(s));
    }

  }
}
