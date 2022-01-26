package edu.duke.ch450.battleship;

  /** 
   *This class handles textual display of 
   *a Board(i.e., converting it to a string to show
   *to the user).
   *It supports two ways to display the Board:
   *one for the player's own board, and one for the 
   * enemy's board.
   */
public class BoardTextView{
    /**
     *The Board to display
     */
    private final Board<Character> toDisplay;
    /**
     * Constructs a BoardView, given the board it will display.
     * @param toDisplay is the Board to display
     */
    public BoardTextView(Board<Character> toDisplay){
      this.toDisplay = toDisplay;
      if(toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26){
        throw new IllegalArgumentException(                                    "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
      }
    }
    public String displayMyOwnBoard(){
      /**
       * Display the board, and it consist of three parts: header, middle, header
       * middle part(Width 1 : 10, Height A : Z)  
       */
      String header = makeHeader();
      String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
      String middle = "";
      for(int i =0 ; i < toDisplay.getHeight(); i++){
        StringBuilder sb = new StringBuilder();
        sb.append(alphabet[i]);
        sb.append(" ");
        for(int j = 0 ; j < toDisplay.getWidth(); j++){
          sb.append(" ");
          if(j != toDisplay.getWidth() - 1){
            sb.append("|");
          }          
        }
        sb.append(" ");
        sb.append(alphabet[i]);
        sb.append("\n");
        middle += sb.toString();
      }    
      String result = header + middle + header;        
      return result; // this is a placeholder for the moment
    }

  
  /**
   * This makes the header line, e.g. 0|1|2|3|4\n
   *
   *@return the String that is the header line for the given board
   */
  String makeHeader(){
    StringBuilder ans = new StringBuilder("  ");// README shows two space at
    String sep = ""; //start with nothing to separate, then switch to | to separate
    for(int i = 0; i < toDisplay.getWidth(); i++){
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }
}
