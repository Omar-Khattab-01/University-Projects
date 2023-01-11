import java.io.*;

public class CopyAndWrite {

    public static void copy( String fileName, String fileName2 ) 
        throws IOException, FileNotFoundException {  

        BufferedReader in = new BufferedReader( new InputStreamReader( new FileInputStream(fileName)));
        File f = new File(fileName2);
        OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(f));
        String line;
        while((line = in.readLine())!= null){
            line+="\n";
            out.write(line);
        }
        in.close();
        out.close();
    
    }

    public static void main( String[] args ) 
        throws IOException, FileNotFoundException {

        // if ( args.length != 1 ) {
        //     System.out.println( "Usage: java Copy file" );
        //     System.exit( 0 );
        // }

        copy( "A4.pdf" ,"Other.txt");

    }
}