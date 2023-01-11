import java.io.*;
import java.net.*;
public class FetchAndPrint {
    
    public static void printWeb(String webPage) throws IOException {
        BufferedReader input = new BufferedReader( new InputStreamReader( new URL(webPage).openStream()));
        String line;
        while ((line = input.readLine()) != null){
            System.out.println(line);
        }
    }
    
    public static void main(String[] args)throws IOException {
        if(args.length != 1){
            System.out.println("Please Enter only the Webpage url");
            System.exit(0);
        }
        printWeb(args[0]);
    }
}
