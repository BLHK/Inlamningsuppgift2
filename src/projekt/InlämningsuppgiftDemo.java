package projekt;
import java.io.*;
import java.nio.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Inl�mningsuppgiftDemo {
	public static void main(String[] args) throws Exception
    {
        // Mata in Namn eller Personnr som string
        // Programmer l�ser igenom textfilen ifall det finns n�gon som har det namnet eller pNr
        //J�mnf�r med dagens datum ifall det var �ver ett �r sedan personen betalade f�r sitt medlemskap
         
         
        String firstLine;
        String secondLine;
        String[] personDataParts;
         
        String person = JOptionPane.showInputDialog("Ange ditt fulla namn eller personNr.");
         
         
        LocalDate now = LocalDate.now();
        LocalDate yearAgo = now.minusYears(1).minusDays(1);
        
         
         
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         
        Path outFilePath = Paths.get("src\\inl�mningsuppgift2\\Utskrift.txt");
         
        Writer output;
         
        try {
            BufferedReader bufIn = new BufferedReader
                    (new FileReader ("src\\inl�mningsuppgift2\\Customers.txt"));
           
        
            Scanner fileScanner = new Scanner(bufIn);
             
             
            while(fileScanner.hasNext()){
                firstLine = fileScanner.nextLine();
                personDataParts = firstLine.split(",");
                 
                if(fileScanner.hasNext()) {
                    secondLine = fileScanner.nextLine();
                     
                    if(personDataParts[0].equals(person) || personDataParts[1].trim().equals(person)) {
 
                        LocalDate anv�ndarDatum = LocalDate.parse(secondLine);
                         
                        if(anv�ndarDatum.isAfter(yearAgo)) {
                            System.out.println("Du f�r komma in!");
                             
                             
                            FileWriter fw = new FileWriter("src\\inl�mningsuppgift2\\Utskrift.txt", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter out = new PrintWriter(bw);
                            out.println(personDataParts[0] + "," + personDataParts[1]);
                            out.println(now);
                            out.close();
                             
                             
                        }else {
                            System.out.println("Ditt medlemskap �r slut.");
                        }
                    }
                }
                 
            }
        }
        catch (Exception e){
            //System.out.println("asd");
        }
    }
}
