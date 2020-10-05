import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Cartman {

    public Cartman(){

    }
    
    public void lecture() throws FileNotFoundException {
        String name="C:/Users/AntoineV/OneDrive/5ème année -M2-/Cours/M2_S1/Concept_Objet/Projet/Insultes2.txt";
        File file = new File(name);
        Scanner in = new Scanner(file);

        while(in.hasNextLine()){
            String line = in.nextLine();
            System.out.println(line);
        }
        in.close();
    }



}
