package edu.duke.ch450.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final V1ShipFactory factory;
  final V2ShipFactory factory2;
  String name;
  Character identity;
  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
  final HashMap<Character, String> shipMap;

  public String getName() {
    return name;
  }

  /**
   * setupShipMap defines the categories of ship
   */
  protected void setupShipMap() {
    shipMap.put('s', "Submarine");
    shipMap.put('d', "Destroyer");
    shipMap.put('b', "Battleship");
    shipMap.put('c', "Carrier");
  }

  /**
   * setupShipCreationMap is the main location to ask factory to create the ships
   */
  protected void setupShipCreationMap() {
    shipCreationFns.put("Submarine", (p) -> factory.makeSubmarine(p));
    shipCreationFns.put("Destroyer", (p) -> factory.makeDestroyer(p));
    shipCreationFns.put("Battleship", (p) -> factory2.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> factory2.makeCarrier(p));
  }

  /**
   * setupShipCreationList is the location to define the number of each ship in
   * the game
   */
  protected void setupShipCreationList() {
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }

  /**
   * @param theBoard,    is the Board for the player
   * @param inputReader, helps to read the input
   * @param factory,     helps to generate a new ship
   * @param name,        player's name
   * @param identity,    if the character is 'A', it means the player is human. If
   *                     the identity is not 'A', it means the player is not
   *                     human.
   */
  public TextPlayer(Board<Character> theBoard, BufferedReader inputReader, PrintStream out, V1ShipFactory factory,
      V2ShipFactory factory2, String name, Character identity) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputReader;
    this.out = out;
    this.factory = factory;
    this.factory2 = factory2;
    this.name = name;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    this.shipMap = new HashMap<Character, String>();
    this.identity = identity;
  }

  /**
   * readPlacement will print out the prompt in the first time And then ask and
   * collect for the user about the Placement that they wanna put the ship return
   * the Placement!
   */

  public Placement readPlacement(String prompt, String shipName) throws IOException, EOFException {
    out.println(prompt);
    String result = "";
    while (true) {
      String s = inputReader.readLine();
      try {
        if (s == null) {
          throw new EOFException("The Input Is Empty!");
        }
        if (s.length() != 3) {
          throw new EOFException("Invalid Input Length!");
        }
        String c = s.substring(0, 2);
        Coordinate coordi = new Coordinate(c);
        if (theBoard.whatIsAtForSelf(coordi) != null) {
          throw new EOFException("The Position At Here Is Already Occupied!");
        }
        Placement p1 = new Placement(s, shipName);
        result = s;
        break;

      } catch (Exception e) {
        System.out.println(e.getMessage());
        // readPlacement(prompt, shipName);
        // out.println(prompt);
        // s = inputReader.readLine();
      }
    }
    return new Placement(result, shipName);
  }

  /**
   * doPlacementPhase() will printout an empty board first And then print out the
   * prompt And then call the doOnePlacement()
   */
  public void doPlacementPhase() throws IOException {
    // TASK 16 LINE: 230 - 244
    if (identity == 'A') {
      out.println(view.displayMyOwnBoard());
      String prompt = "Player " + name + ":"
          + " you are going to place the following ships (which are all rectangular). For each ship, type the coordinate of the upper left side of the ship, followed by either H (for horizontal) or V (for vertical).  For example M4H would place a ship horizontally starting at M4 and going to the right.  You have\n\n"
          + "2 \"Submarines\" ships that are 1x2\n" + "3 \"Destroyers\" that are 1x3\n"
          + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n";
      out.println(prompt);
      setupShipCreationMap();
      setupShipCreationList();
      for (String s : shipsToPlace) {
        doOnePlacement(s, shipCreationFns.get(s));
      }
    } else {
      setupShipCreationMap();
      setupShipCreationList();
      for (String s : shipsToPlace) {
        doOnePlacement(s, shipCreationFns.get(s));
      }
    }

  }

  /**
   * doAnotherPlacement is to move the original ship into other place
   * @param shipName just gives the type of the board
   * @param newPlacement just give the Lamda function that help to generate a whole new ship with the new Placement
*/
  public Ship<Character> doAnotherPlacement(String shipName,
      HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns, Placement newPlacement)
      throws IOException {
    Ship<Character> s = shipCreationFns.get(shipName).apply(newPlacement);
    theBoard.tryAddShip2(s);
    return s;
  }

  /**
   * doOnePlacement() will print out the prompt And then create a placement and
   * coordinate for the ship and then add the ship into the board
   */

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    if (identity == 'A') {
      Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?", shipName);
      Ship<Character> s = createFn.apply(p);
      theBoard.tryAddShip(s);
      out.print(view.displayMyOwnBoard());
    } else {
      Placement p;
      if (shipName == "Submarine") {
        p = new Placement("A0H", shipName);
      } else if (shipName == "Destroyer") {
        p = new Placement("B0H", shipName);
      } else if (shipName == "Battleship") {
        p = new Placement("D0U", shipName);
      } else {
        p = new Placement("G0U", shipName);
      }
      Ship<Character> s = createFn.apply(p);
      out.println("I'm computer!");
      theBoard.tryAddShip(s);
      out.println(view.displayMyOwnBoard());
    }

  }

  /**
   * playOneTurn() could execute one whole round for the game
   * In addition to this, the identity == 'A' means that it is human player.
   * If the player is computer, it will execute the else statement
*/
  public void playOneTurn(Board<Character> enemyBoard, String enemyName) throws IOException {
    if (identity == 'A') {
      setupShipMap();
      out.println("Player " + name + "'s turn:");
      String enemyHeadLine = "Player " + enemyName + "'s ocean";
      BoardTextView enemyBoardTextView = new BoardTextView(enemyBoard);
      out.println(view.displayMyBoardWithEnemyNextToIt(enemyBoardTextView, "Your ocean", enemyHeadLine));
      Character decision = readOptions();
      optionsExecutor(decision, enemyBoard, enemyName);
    } else {
      setupShipMap();
      optionsExecutor('F', enemyBoard, enemyName);
    }
  }

  /**
   * readOptions() can read the option which is typed by the user, and redirect to the different mode for the user.
   */
  public Character readOptions() throws IOException {
    String s = null;
    Character decision = 'F';
    Boolean indicator = false;
    HashMap<Character, Integer> options = theBoard.getOptionsMap();
    do {
      out.println("Possible actions for Player " + name + ":\n\n" + " F Fire at a square\n"
          + " M Move a ship to another square (" + options.get('M') + " remaining)\n" + " S Sonar scan ("
          + options.get('S') + " remaining)\n" + "Player " + name + ", what would you like to do?\n");
      s = inputReader.readLine();
      try {
        if (s == null) {
          throw new IOException("The Input Of Options Should Not Be Empty!\n");
        }

        if (s.length() != 1) {
          throw new IOException("The Format of Options Input Is Not Correct!\n");
        }
        s = s.toUpperCase();
        char c = s.charAt(0);
        if (options.containsKey(c) == false) {
          throw new IOException("The Input Of Options Is Invalid!\n");
        }
        if (options.get(c) <= 0) {
          throw new IOException("The Remaining Time Of Action Is Not Enough!\n");
        }
        int counter = options.get(c);
        options.put(c, (counter - 1));
        decision = c;
        indicator = true;

      } catch (IOException e) {
        out.print(e.getMessage());
        continue;
      }

    } while (indicator == false);
    return decision;
  }

  /**
   * This is the redirection method to help redirect!
   */
  public void optionsExecutor(Character decision, Board<Character> enemyBoard, String enemyName) throws IOException {
    /////// HAVEN'T FINISHED!
    decision = Character.toUpperCase(decision);
    if (decision == 'F') {
      optionFire(enemyBoard);
    }
    if (decision == 'S') {
      optionSona(enemyBoard);
    }
    if (decision == 'M') {
      optionMove(enemyBoard);
    }

  }

  /**
   * This is whole the thing the game will do when the user just choose the MOVING MODE
   * @param enemyBoard is the board of enemyBoard
   * It will read the new input, and create a new Placement, and then doAnotherPlacement for the new ship.
*/
  public void optionMove(Board<Character> enemyBoard) throws IOException {
    boolean indicator = false;
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> oldShip = f.makeSubmarine(new Placement("A0H")) ;
     Ship<Character> newShip = f.makeSubmarine(new Placement("A0H")) ;
     String s = "";
    do {
      try {
        out.println("Please input the Coordinate that you wanna move from:");
        s = inputReader.readLine();
        oldShip = getOldShip(s);
        Placement newPlacement = getNewPlacement(oldShip.getName());
        newShip = doAnotherPlacement(oldShip.getName(), shipCreationFns, newPlacement);
        indicator = true;
      } catch (IOException e) {
        out.println(e.getMessage());
        continue;
      }
    } while (indicator == false);

    HashSet<Integer> damageNumber = oldShip.getDamageNumber();
    HashSet<Coordinate> newMapping = newShip.mappingNewShip(damageNumber);
    for (Coordinate coordi : newMapping) {
      // newShip = theBoard.fireAt(coordi);
      newShip.recordHitAt(coordi);
    }
    // remove old ship
    theBoard.removeShip(oldShip.getOneCoordinate());
    out.print(view.displayMyOwnBoard());
  }
  
  /**
   * getNewPlacement() will get the new Placement for the new ship under the MOVING MODE
   * It will also double check whether the placement could work in the new place.
*/
  public Placement getNewPlacement(String shipName) throws IOException {
    String s = null;
    Boolean indicator = false;
    Placement newPlacement = null;
    do {
      out.println("Please input the placement of the ship you wanna move:");
      s = inputReader.readLine();
      try {
        if (s == null) {
          throw new IOException("The input of placement of ship is empty or invalid!\n");
        }
        s = s.toUpperCase();
        if (s.length() != 3) {
          throw new IOException("The input length of the placement is wrong!");
        }
        newPlacement = new Placement(s, shipName);
        indicator = true;
      } catch (IOException e) {
        out.print(e.getMessage());
        continue;
      }
    } while (indicator == false);
    return newPlacement;
  }

  /**
   * getOldShip() will help to get the old ship, because if the user choose MOVING MODE, and the program need the original ships to do the adjustment.
*/
  public Ship<Character> getOldShip(String s) throws IOException {
    //String s = null;
    Boolean indicator = false;
    Ship<Character> moveShip = null;
    do {
      try {
        if (s == null) {
          throw new IOException("The input of Coordinate of ship is empty or invalid!\n");
        }
        s = s.toUpperCase();
        if (s.length() != 2) {
          throw new IOException("The input length of the Coordinate is wrong!");
        }
        Coordinate coordi = new Coordinate(s);

        if (theBoard.getShipFromCoordinate(coordi) != null) {
          moveShip = theBoard.getShipFromCoordinate(coordi);
        } else {
          throw new IOException("There is no any ship in the position that you wanna move ship!");
        }
        indicator = true;
      } catch (IOException e) {
        out.print(e.getMessage());
        continue;
      }

    } while (indicator == false);
    return moveShip;
  }

  /**
   * optionFire() this is the whole execution of the FIRE MODE
*/
  public void optionFire(Board<Character> enemyBoard) throws IOException {
    Coordinate fireCoordinate = new Coordinate(0, 0);
    if (identity == 'A') {
      fireCoordinate = readCoordinate();
      Character loc = enemyBoard.whatIsAtForSelf(fireCoordinate);
      Ship<Character> fireShip = enemyBoard.fireAt(fireCoordinate);
      if (fireShip == null) {
        out.println("You missed!");
      } else {
        String shipName = shipMap.get(loc);
        out.println("You hit a " + fireShip.getName() + "!");
      }
    }

    else {
      Coordinate fireCoordinate2 = new Coordinate(0, 1);
      Character loc = enemyBoard.whatIsAtForSelf(fireCoordinate2);
      Ship<Character> fireShip = enemyBoard.fireAt(fireCoordinate2);

      if (fireShip == null) {
        out.println("Computer missed!");
      } else {
        String shipName = shipMap.get(loc);
        out.println("Computer hit a " + fireShip.getName() + "!");
      }
    }

  }
  
  /**
   * readCoordinate() can help to read the coordinate for the fire place.
   * Additionally, it will also do the double check to see whether the fire coordinate is valid or not.
*/
  public Coordinate readCoordinate() throws IOException {
    String s = null;
    Boolean indicator = false;
    Coordinate fireCoordinate = null;
    do {
      out.println("Please enter the fire location:");
      s = inputReader.readLine();
      if (s == null) {
        throw new IOException("The input of coordinate of fire is empty or invalid!\n");
      }
      try {
        fireCoordinate = new Coordinate(s);
        indicator = fireCoordinate.boundCheck(theBoard.getWidth(), theBoard.getHeight());
        if (indicator == false) {
          out.println("The coordinate is out of the bound!");
        }
      } catch (IllegalArgumentException illegalArg) {
        out.print(illegalArg.getMessage());
        continue;
      }

    } while (indicator == false);
    return fireCoordinate;
  }
  /**
   * optionSonar is the whole processure for the SONAR MODE
*/
  public void optionSona(Board<Character> enemyBoard) throws IOException {
    Coordinate sonaCoordinate = readSonaCoordinate(enemyBoard);
    HashMap<String, Integer> result = sonaCoordinate.sonaCheck(enemyBoard);
    int Submarine = result.get("Submarine");
    int Destroyer = result.get("Destroyer");
    int Battleship = result.get("Battleship");
    int Carrier = result.get("Carrier");
    printSonaResult(Submarine, Destroyer, Battleship, Carrier);

  }

  /**
   * readSonaCoordinate() just help to get the location of the sonar
   * It will also the check whether the location of sonar is valid or not?
*/
  public Coordinate readSonaCoordinate(Board<Character> enemyBoard) throws IOException {
    String s = null;
    Boolean indicator = false;
    Coordinate SonaCoordinate = null;
    do {
      out.println("Please enter the Sona location:");
      s = inputReader.readLine();
      try {
        SonaCoordinate = new Coordinate(s);
        indicator = SonaCoordinate.boundCheck(theBoard.getWidth(), theBoard.getHeight());
        if (indicator == false) {
          out.println("The Coordinate Of Sona Is Out Of The Bound!\n");
        }
      } catch (IllegalArgumentException illegalArg) {
        out.print(illegalArg.getMessage());
        continue;
      }

    } while (indicator == false);
    return SonaCoordinate;
  }
  
  public void printSonaResult(int sub, int des, int bat, int car) {
    out.println("Submarines occupy " + sub + " squares");
    out.println("Destroyers occupy " + des + " squares");
    out.println("Battleships " + bat + " squares");
    out.println("Carriers occupy " + car + " squares");
  }

  public boolean checkLose() {
    return theBoard.allSunk();
  }
}
