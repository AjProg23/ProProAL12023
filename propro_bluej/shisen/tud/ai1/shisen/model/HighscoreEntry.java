package tud.ai1.shisen.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasse die einen einzelnen Highscore-Eintrag repraesentiert.
 *
 * @author Andrej Felde, Daniel Stein, Nicklas Behler
 *
 */
public class HighscoreEntry implements Comparable<HighscoreEntry> {

    /**
     * Datum des gespielten Spiels
     */
    private LocalDateTime date;

    /**
     * Benoetigte Zeit in Sekunden
     */
    private double duration;

    /**
     * Erreichte Punktzahl
     */
    private int score;

    /**
     * Separator zum parsen und schreiben der Highscore-Datei
     */
    private static final String separator = ";";

    /**
     * Formatter um String in LocalDateTime zu formatieren
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    /**
     * Konstruktor des HighscoreEntrys erzeugt neue Instanz eines
     * Highscore-Eintrags.
     *
     * @param date     Datum des gespielten Spiels.
     * @param score    Erspielter Score.
     * @param duration Benoetigte Zeit in Sekunden.
     * 
     * If the parameters are valid, they get initialized with appropriate values.
     */
    public HighscoreEntry(LocalDateTime date, int score, double duration) {//Ex.4b
        if(duration >= 0 && score >= 0 && score < 1000){ 
           date = null;
           score = 0;
           duration = 00;
        }
    }

    /**
     * 
     */
    public HighscoreEntry(String data) {//Ex.4c
      
    }

    /**
     * @param date is the field that rappresents the date and time of the game
     * @param score is the field that rappresents the highscore achieved
     * @param duration  is the field that rappresents the used time in seconds
     * 
     * If we insert the wrong value so that 
     * 1 of these conditions is true, the system throws an IllegalArgumentException with 
     * a reminder on screen of the reason that shouldn't happen and the program will stop.
     * 
     * While when the parameter date is null, the method validate() throws an IllegalArgumentException
     * ,by using a try-catch block we catch the exception and we print on screen the fact 
     * that an exception has been thrown. This action does not stop the program from working because
     * we just catched the exception. The program will still be executed just as we want.
     */
    public void validate(LocalDateTime date, int score, double duration) { //Ex.4a
         
        if(duration < 0){
          throw new IllegalArgumentException("duration was negative");
        }
        
        if(score < 0 || score > 1000){
          throw new IllegalArgumentException("score was negative or greater than 1000"); 
        }

         try{ 
             
             if(date == null ){
                   throw new IllegalArgumentException("date was null");
             } 
         } 
        
          catch(IllegalArgumentException e){
            System.out.println("IllegalArgumentException was thrown");
        }
    }

    /**
     * method that compares the HighscoreEntry with the @param obj.
     * First we check that the param obj is not null,we create a default obj and a default
     * HighscoreEntry. 
     * If obj and HighscoreEntry are of the same type then I compare the attributes of the 2 objects
     * to see if they are the same. If they are the same, the boolean @param Answer will be switched
     * to true, or else remain false.
     * @return Answer as a final value
     */
    @Override
    public boolean equals(Object obj) {//Ex.4d
        assert obj != null: "obj is null";
        obj = new Object();
        boolean Answer = false;
        HighscoreEntry a = new HighscoreEntry(date,score,duration);
        if(obj instanceof HighscoreEntry){
           Answer = obj.equals(a); 
          return Answer;
        }
        return Answer;
    }

    /**
     * Getter fuer date als String.
     * 
     * @return String - Spieldatum und Uhrzeit
     */
    public String getDate() {
        return String.format("%02d.%02d. %02d:%02d", date.getDayOfMonth(), date.getMonthValue(), date.getHour(),
                date.getMinute());
    }

    /**
     * Getter fuer die Punktzahl.
     * 
     * @return Erreichte Punktzahl
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter fuer die Dauer.
     * 
     * @return Benoetigte Zeit in Sekunden
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Getter, um das Datum in ein Format zum Speichern zu ueberfuehren.
     * 
     * @return String dd.MM.yyyy hh:mm
     */
    private String dateToSaveFormat() {
        return String.format("%02d.%02d.%02d %02d:%02d", date.getDayOfMonth(), date.getMonthValue(), date.getYear(),
                date.getHour(), date.getMinute());
    }

    /**
     * @param o is an object of type HighscoreEntry 
     * If the current HighscoreEntry(this) score is less than the score of the passed object o
     * it @returns 1; 
     * If opposite then @return is -1;
     * If this.score is equal to o.score than I have to compare their attibute field of duration 
       in an inverted way than before. If both durations are equal than I @return 0 as a value.
    */
    @Override
    public int compareTo(HighscoreEntry o) {// TODO Aufgabe 4.e
            
            if(this.score < o.score){
             return 1; //gives a positive number
            } 
            if(this.score > o.score){
             return -1; //gives a negative number
            } 
            else{
                if(this.duration < o.duration){
                  return -1;
                }
                if(this.duration > o.duration){
                  return 1;
                }
                else{
                  return 0;
                }
            }
        }


    /**
     * Diese Methode gibt die String-Repraesentation des Objekts zurueck.
     * 
     * @return String-Repraesentation
     */
    @Override
    public String toString() {
        return dateToSaveFormat() + separator + score + separator + duration;
    }
}
