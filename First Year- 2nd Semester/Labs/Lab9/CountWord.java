import java.io.*;
import java.net.*;;

public class CountWord {
    
    public static void  findAndCount(String fileName, String word)throws IOException,FileNotFoundException{

        //BufferReader
        BufferedReader input = new BufferedReader( new InputStreamReader(new FileInputStream(fileName)));
        int counter = 0;
        int index;
        String line;
        while ((line = input.readLine()) != null){
            if ((index =line.indexOf(word)) != -1){
                counter++;
                line = line.substring(index+word.length());
                while ((index =line.indexOf(word)) != -1 && !line.trim().equals(word)){
                    line = line.substring(index+word.length());
                    counter++;
                }
            }
        }
        input.close();
        System.out.println("The word: "+word+" Occurred :"+counter+" times.");
    }

    public static void  findAndCountWeb(String fileName, String word)throws IOException,FileNotFoundException{

        //BufferReader
        // URL address = new URL("http://www.google.ca");
        // InputStreamReader is = new InputStreamReader(address.openStream());
        BufferedReader input = new BufferedReader( new InputStreamReader((new URL(fileName)).openStream()));
        int counter = 0;
        int index;
        String line;
        while ((line = input.readLine()) != null){
            if ((index =line.indexOf(word)) != -1){
                counter++;
                line = line.substring(index+word.length());
                while ((index =line.indexOf(word)) != -1 && !line.trim().equals(word)){
                    line = line.substring(index+word.length());
                    counter++;
                }
            }
        }
        input.close();
        System.out.println("The word: "+word+" Occurred :"+counter+" times.");
    }
    
    public static void main(String[] args)throws IOException, FileNotFoundException  {
    
    if(args.length != 2){
        System.out.println("Please Enter the Filename followed by the word");
        System.exit(0);
    }
    findAndCount(args[0], args[1]);
    // findAndCountWeb("https://beginnersbook.com/2013/12/java-string-substring-method-example/","java");
    

    }
}
