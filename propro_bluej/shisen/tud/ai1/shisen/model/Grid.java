package tud.ai1.shisen.model;

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
    
    private static IToken[][] grid = null;    //Ex.3a
    private IToken selectedTokenOne = null;    //Ex.3a
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
    
    public Grid(String a){   //Ex.3b
       // parseMap(String path);  
        fillTokenPositions();
        int score = 0;
    }
     
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
     * @return Grid
     */
     public  IToken[][] getGrid(){ //Ex.3d
         return grid;
     }    
     
     
        /**
     * Liefert die gerade aktiven Tokens zurueck.
     * 
     * @return Aktive Tokens
     */
     public  IToken[] getActiveTokens(){ //Ex.3d  
        return  selectedTokenTwo && selectedTokenOne; 
     }
     
     /**
     * Tested ob beide selektierten Tokens angeklickt wurden.
     * 
     * @return True wenn beide selektierten Token angeklickt wurde
     */
    public boolean bothClicked(){
     return bothClicked;
    }
    
    
     /**
     * TODO: Aufgabe 3f
     */
     
     /**
     * TODO: Aufgabe 3g
     */
     
     /**
     * TODO: Aufgabe 3h
     */

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
