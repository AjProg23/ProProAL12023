package tud.ai1.shisen.model;


/**
 * Enumeration class TokenState - TokenState enumeraton type represents the state of a field 
 * and is used to mark it with one of these 4 states: DEFAULT, CLICKED, WRONG, SOLVED 
 *
 * @author (Ahed M A ALHijjawi)
 * @version (18,06,23)
 */
public enum TokenState 
{
    DEFAULT, CLICKED, WRONG, SOLVED    //Exercise 1
    
    /*
     * As requested in exercise 1: Created an enum called TokenState and set values
     *  to DEFAULT, CLICKED, WRONG, SOLVED. Each value represents a state of field so for example
     *  DEFAULT = state in which the field is "default" or hasn't had a change to it yet
     *  CLICKED = state in which the field has been clicked on and now we are about to click
     *  on another field to see wether they are compatible or not. 
     *  => If they are compatible then both fields that have been clicked on gain the 
     *  SOLVED state = state in which we state that the fields has already been solved and
     *  that we should now choose other fields. 
     *  => If not then the fields gain the WRONG state = state in which why imply that the 
     *  pair of fields chosen are incompatible and thus wrong. 
     */
}
