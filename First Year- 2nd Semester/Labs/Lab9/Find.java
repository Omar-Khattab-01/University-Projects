// Prints all the lines containing a given word. For each line
// containing the word, it prints the line number followed by the line
// itself.

import java.io.*;

public class Find {

    public static void find( String fileName, String word ) 
        throws IOException, FileNotFoundException {

		BufferedReader input;
		
		//create your BufferedReader
        input =  new BufferedReader(new InputStreamReader( new FileInputStream(fileName) ));

        
        int lineNumber = 0;
        String line="";

        //create a while loop to read your file line by line
			//Verify if your word is in the line being read
        boolean found = false;
        while ((line = input.readLine()) != null ){
            if(line.indexOf(word) != -1){
                found = true;
                System.out.println("Line Number: "+ ++lineNumber +"--> "+line);
            }else{
            lineNumber++;
            }
        }
        //close your file
        input.close();
		if(!found)
            System.out.println("Word :("+word + ") not found in "+fileName);
        
            
        
    }

    public static void main( String[] args ) 
        throws IOException, FileNotFoundException {

        if ( args.length != 2 ) {
            System.out.println( "Please Enter the Filename followed by the Word that you desire to Find" );
            System.exit( 0 );
        }

        find( args[0], args[1] );
        // String o="121: sdaf,asa";
        // String[] ok = o.split(":");
        // System.out.println(ok[0]);
        // for (int i = 0;i<ok.length;i++)
        //     System.out.println(ok[i]);

    }
}


//output example
// > java Find Find.java IOException
// 10:   throws IOException, FileNotFoundException {
// 28:  throws IOException, FileNotFoundException {