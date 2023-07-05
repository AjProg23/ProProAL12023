package tud.ai1.shisen.model;

import java.io.FileReader;
import java.io.IOExceptions;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import tud.ai1.shisen.util.Consts;
import tud.ai1.shisen.util.PathFinder;

/**
 * Diese Klasse repraesentiert das Spielfeld.
 * 
 * @author Nicklas Behler, Sebastian C, Lennart Fedler, Niklas Grimm, Robert
 *         Jakobi, Max Kratz, Niklas Vogel
 *
 */
public class Grid implements IGrid {

    private int waitTime = 1000;
    private TokenState destiny;
    private long currTime;
    private boolean timerActive = false;
    private List<IToken> list;
    private static int score = 0;
    
    private static IToken[][] grid;    //Ex.3a, grid represents the whole matrix of fields
    private IToken selectedTokenOne = null;    //Ex.3a, intuitive
    private IToken selectedTokenTwo = null;    //Ex.3a

   

    /**
     * Konstruktor, der ein zufaelliges Grid zum Testen erzeugt.
     */
    public Grid() {
        final IToken[][] demoGrid = new IToken[10][10];
        for (int x = 0; x < demoGrid.length; x++) {
            for (int y = 0; y < demoGrid[x].length; y++) {
                demoGrid[x][y] = new Token(1);
            }
        }
        grid = demoGrid;
    }
    
    /**
     * @param String a is a file path to a map in the form of a string. 
     */
    public Grid(String a){   //Ex.3b
        parseMap(String path);  
        fillTokenPositions();  
        int score = 0;  //starts score with 0
    }
     
    /**
     * @param int x stands for columns and int y for rows in the matrix.
     * together they rappresent the coordinates of a single field in the matrix.
     * 
     * @1.return: returns the token that lies on the given coordinates of the matrix, but only 
     * if x and y are positive (there are no negative coordinates in a matrix)
     * 
     * @2.return: if either x or y are negative then we return the value null because the
     * token at coordinates (x,y) cannot be selected as it is outside of the matrix. s 
     */
     public IToken getTokenAt(int x, int y){ //Ex.3c
         if(x >= 0 && y >= 0){
            return selectedTokenOne;
         }
         else{
             return null;
         }   
     }
     
     
       /**
     * Gibt das Grid zurueck.
     * 
     * @return Grid of the constructor
     */
     public  IToken[][] getGrid(){ //Ex.3d
         return grid;
     }    
     
     
        /**
     * Liefert die gerade aktiven Tokens zurueck.
     * 
     * @return Aktive Tokens in a form of an IToken array of length 2 with the values of selectedTokenOne
     * and selectedTokenTwo inside. 
    */
     public  IToken[] getActiveTokens(){ //Ex.3d  
        IToken[] AktiveTokens = {selectedTokenOne,selectedTokenTwo}; 
        return   AktiveTokens;
     }
     
     /**
     * Tested ob beide selektierten Tokens angeklickt wurden.
     * 
     * @return True when 2 Tokens have been clicked on, that happens when both SelectedTokenOne 
     * and SelectedTokenTwo are different from null because null is the default value where no 
     * token have yet been clicked on. If they 
     * are different from null it means that they contain new values and thus meaning that 
     * 2 Tokens have been clicked on. 
     * 
     * @return false if either of SelectedTokenOne or SelectedTokenTwo are still null.
     */
    public boolean bothClicked(){//Ex.3e
        if(selectedTokenOne != null && selectedTokenTwo != null){
           return true;
        } 
        else{
          return false;
        }
    }
    
    
     /**
     * Falls der uebergebene Token derzeit angewaehlt ist, wird er abgewaehlt.
     * If the @param token is selected(!=null for the reasons mentioned above) we set its state
     * to default and deselect it (token = null)
     * 
     * @param Token Der abzuwaehlende Token.
     */
    public void deselectToken(IToken token){ //Ex. 3f 
         if(token != null){
           TokenState state = TokenState.DEFAULT;
           token = null;
         }
    }
    
    /**
     * Deselektiert die beiden Token.
     * If both (&&)tokens are already selected(!=null) we deselect them (= null)
     */
    public void deselectTokens(){//Ex. 3f 
      if(selectedTokenOne != null && selectedTokenTwo != null){
          selectedTokenOne = null;  
          selectedTokenTwo = null;
      }
    }
     
    
     //tips: when a token is solved it gets the -1 value && 
     //  TokenState state = TokenState.SOLVED;
     // private List<IToken> list;
     // for(int i = 0; i < list.size(); i++)
     /**
     * We have to iterate the List of Itokens so we give and indicator int i a starting value
     * so that we can scroll the list.
     * While I is less than the list size (we dont want the OutOfBounds error message) and
     * the current IToken we are inspecting is "Solved", we put the value of @return to true and 
     * increment (scroll the list further)the indicator i. 
     * If we have a case where an Itoken is not solved, we put the value of @return to false and 
     * break the loop so that the final @return value of the method is false.
     * 
     * 
     */
     public static boolean isSolved(){  //Ex. 3g
        List<IToken> list = null;
        TokenState TokenIsSolved = TokenState.SOLVED; 
        boolean GridIsSolved = false;
        int i = 0;
           while(i < list.size()){
               if(IToken == TokenIsSolved){
                return GridIsSolved = true;
                i++;
               }
               else{
                 return GridIsSolved = false;
                 break;
                } 
           }
            return GridIsSolved;
        }
     
        /**
         * @param String path is a path to a map file and is used to construct the grid matrix of 
         * tokens. 
         * 
         * 
         */
     private Token[][] parseMap(String path){ //Ex.3h
       TokenState state = TokenState.DEFAULT; //initial state
       Vector2f pos;  //position on the grid
       int x; int y;
       pos = new Vector2f(x,y);
     }

    /**
     * Updated den Score um incr. Sollte der Score anschliessend negativ sein, so
     * wird er bis auf 0 dekrementiert.
     * 
     * @param incr Zahl um die Score erhoeht / erniedrigt werden soll.
     */
    public void updateScore(final int incr) {
        if (score + incr >= 0) {
            score += incr;
        } else {
            score = 0;
        }
    }

    /**
     * Getter fuer score.
     *
     * @return Aktueller score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Teile jedem Token seine Position im Array mit.
     */
    private void fillTokenPositions() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y].setPos(new Vector2f(x, y));
            }
        }
    }

    /**
     * Waehle einen Token auf dem Spielfeld aus und loese diesen falls moeglich.
     * 
     * @param Token Angeklickter Token.
     */
    @Override
    public void selectToken(final IToken token) {
        if (this.selectedTokenOne == null) {
            this.selectedTokenOne = token;
            selectedTokenOne.setTokenState(TokenState.CLICKED);
        } else if (this.selectedTokenTwo == null) {
            this.selectedTokenTwo = token;
            selectedTokenTwo.setTokenState(TokenState.CLICKED);
            this.list = PathFinder.getInstance().findPath(this, (int) this.selectedTokenOne.getPos().x,
                    (int) this.selectedTokenOne.getPos().y, (int) this.selectedTokenTwo.getPos().x,
                    (int) this.selectedTokenTwo.getPos().y);
            if (this.list == null || this.list.size() == 0
                    || !this.selectedTokenOne.getDisplayValue().equals(this.selectedTokenTwo.getDisplayValue())) {
                this.selectedTokenOne.setTokenState(TokenState.WRONG);
                this.selectedTokenTwo.setTokenState(TokenState.WRONG);
                this.updateScore(Consts.DECREASE_SCORE);
                this.startTimer(Consts.DISPLAY_WRONG_TIME, TokenState.DEFAULT);
            } else {
                for (final IToken tok : this.list) {
                    tok.setTokenState(TokenState.CLICKED);
                }
                this.updateScore(Consts.GAIN_SCORE);
                this.startTimer(Consts.DISPLAY_WRONG_TIME, TokenState.SOLVED);
            }
        }
    }
    
    /**
     * Startet einen Timer (Genutzt fuer Anzeigedauer bei falscher / richtiger
     * Auswahl von zwei Tokens).
     * 
     * @param waitTime Zeit in Sekunden, die gewartet werden soll.
     * @param dest     Ziel Tokenstate.
     */
    private void startTimer(final double waitTime, final TokenState dest) {
        this.timerActive = true;
        this.currTime = System.currentTimeMillis();
        this.waitTime = (int) waitTime * 1000;
        this.destiny = dest;
    }

    /**
     * Prueft ob Anzeigezeit bei falscher/richtiger Auswahl bereits ueberschritten
     * ist. Falls ja wird der entsprechende Code ausgefuehrt.
     */
    @Override
    public void getTimeOver() {
        if (this.timerActive) {
            if (System.currentTimeMillis() - this.currTime > this.waitTime) {
                try {
                    if (this.list != null) {
                        for (final IToken tok : this.list) {
                            tok.setTokenState(TokenState.SOLVED);
                        }
                    }
                    this.selectedTokenOne.setTokenState(this.destiny);
                    this.selectedTokenTwo.setTokenState(this.destiny);
                    this.selectedTokenOne = null;
                    this.selectedTokenTwo = null;
                   this.timerActive = false;
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
