package tud.ai1.shisen.model;
import org.newdawn.slick.geom.Vector2f;
import  tud.ai1.shisen.util.TokenDisplayValueProvider;


/**
 * Write a description of class Token here.
 *
 * @author (Ahed M A ALHijjawi)
 * @version (18,06,23)
 */
public class Token implements IToken
{
    private static int counter = 0; //Ex. 2a
    private static int id;
    private TokenState state;
    private static int value;
    private Vector2f pos;
    

    /**
     * Constructor for objects of class Token
     */
    public Token()
    {
        // initialise instance variables : //Ex. 2b
        this.value = 0;
        this.state = state;
        this.pos = pos; 
        
          id = counter;
          counter = counter++;
    }
    
    public Token(int value)  //contructor 2.0
    {
        // initialise instance variables : //Ex. 2c
        state = TokenState.DEFAULT;
        pos = new Vector2f(0,0);
        
        id = counter;
        counter = counter++;
    }

     /**
     * Gebe Wert des Felds zurueck. 
     * 
     * @return Wert des Felds
     */
     @Override //Ex. 2d
     public int getValue(){
       return value;
     }
     
     /**
     * Gebe TokenState zurueck.
     * 
     * @return TokenState
     */
    @Override   //Ex. 2d
    public TokenState getTokenState(){
      return state;
    }
    
    /**
     * Gebe ID des Felds zurueck.
     * 
     * @return ID des Felds
     */
    @Override   //Ex. 2d
    public int getID(){
      return id;
    }
    
     /**
     * Gebe Position des Felds als Vector2f zurueck.
     * 
     * @return Position des Felds als Vector2f
     */
    @Override  //Ex. 2d
    public Vector2f getPos(){
       return pos;
    }
    
    /**
     * Gebe Anzeige-Wert als String zurueck.
     * 
     * @return Anzeige-Wert als String
     */
    @Override //Ex. 2e
    public String getDisplayValue(){
      return TokenDisplayValueProvider.getInstance().getDisplayValue(value); 
    }
    
     /**
     * Setze TokenState auf uebergebenen Wert.
     * 
     * @param abc TokenState, welcher gesetzt wird.
     */
    public void setTokenState(TokenState newState){   //Ex. 2f
    }
    
     /**
     * Setze Position des Felds auf uebergebenen Vector2f Wert.
     * 
     * @param pos Vector2f Wert, der gesetzt wird.
     */
    public void setPos(Vector2f newPos){        //Ex. 2f
    }
}
