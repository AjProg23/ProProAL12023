package tud.ai1.shisen.model;
import org.newdawn.slick.geom.Vector2f;
import  tud.ai1.shisen.util.TokenDisplayValueProvider;


/**
 * Public class Token - It is a class that stands for a single field on the entire playing field
 *
 * @author (Ahed M A ALHijjawi)
 * @version (18,06,23)
 */
public class Token implements IToken //Ex. 2a
{
    private static int counter = 0; // Generates unique IDs
    private static int id; //enables the field to be clearly identified on the playing field
    private TokenState state; // TokenState that describes the current
    //state of the field (takes value from enum TokenState)
    private static int value; //the value of the field
    private Vector2f pos; //gives the coordinates of each square on the field
    // import org.newdawn.slick.geom.Vector2f;
    

    /**
     * Default Constructor for objects of class Token
     * 
     *    @param value = the value of the field   
     * 
     *    @param  TokenState state = TokenState that describes the current 
     *         state of the field (takes value from enum TokenState)
     * 
     *    @param  Vector2f pos = gives the coordinates of each square on the field
     */
    public Token(int value, TokenState state, Vector2f pos)//Ex. 2b

    {
        // initialise instance variables :       
        this.value = value; //int
        this.state = state; //TokenState
        this.pos = pos;  //Vector2f
        
          this.id = this.counter;
          counter = counter++;
    }
   
    /**
     *  @param value = the value of the field  
     */ 
    public Token(int value)  //contructor 2.0  Ex. 2c
    {
        // initialise instance variables : 
        this.value = value;     
        this.state = TokenState.DEFAULT;
        this.pos = new Vector2f(0,0);
        
        this.id = this.counter;
        counter = counter++;
    }

     /**
     * Gebe Wert des Felds zurueck. 
     * 
     * 
     * @return Wert des Felds
     */
     @Override //Ex. 2d 
     public int getValue(){ //returns the instance variable "value" as int
       return value;
     }
     
     /**
     * Gebe TokenState zurueck.
     * 
     * @return TokenState
     */
    @Override   //Ex. 2d
    public TokenState getTokenState(){//returns the instance variable "state" as TokenState   
      return state;
    }
    
    /**
     * Gebe ID des Felds zurueck.
     * 
     * @return ID des Felds
     */
    @Override   //Ex. 2d
    public int getID(){ //returns the instance variable "id" as int
      return id;
    }
    
     /**
     * Gebe Position des Felds als Vector2f zurueck.
     * 
     * @return Position des Felds als Vector2f
     */
    @Override  //Ex. 2d
    public Vector2f getPos(){ //returns the instance variable "pos" as Vector2f
       return pos;
    }
    
    /**
     * Gebe Anzeige-Wert als String zurueck.
     * 
     * gets the DisplayValue from the class TokenDisplayValueProvider in "Util"
     * We can't call the method TokenDisplayValueProvider.getDisplayValue() because 
     * we have an incompatibility between static and dynamic methods. 
     * That's why we use the .getInstance() method to reach .getDisplayValue(value)
     * 
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
     * sets a new value of type TokenState in state
     *
     */
    public void setTokenState(TokenState newState){   //Ex. 2f
        state = newState;
    }
    
     /**
     * Setze Position des Felds auf uebergebenen Vector2f Wert.
     * 
     * sets a new value of type Vector2f in pos
     * 
     * @param pos Vector2f Wert, der gesetzt wird.
     */
    public void setPos(Vector2f newPos){        //Ex. 2f
        pos = newPos;
    }
}
