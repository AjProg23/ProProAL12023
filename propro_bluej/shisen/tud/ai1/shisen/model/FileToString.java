package tud.ai1.shisen.model;
import java.io.FileReader;
import java.io.IOException;


/**
 * Write a description of class FileToString here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileToString{
    
    public static String ReadFileAsString(String filepath) throws IOException{
        StringBuilder content = new StringBuilder();
    
        try(FileReader reader = new FileReader(filepath){
            int character;
            while((character = reader.read()) != -1){
              content.append((char) character);
            }
        }
        
        return content.toString();
    } 

   public static void main(String[] args){
    String filepath = "C:\Users\Ahed Al Hijjawi\Desktop\ProPro2023\propro_bluej\shisen\assets\maps\001.map";
    
    try{
      String filecontent = readFileAsString(filePath);
      System.out.println(fileContent);
    } catch (IOException e){
      e.printStackTrace();
    }
   }
}
