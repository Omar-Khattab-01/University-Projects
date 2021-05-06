import java.io.*; 

            public class L9 { 

            public static String cat( String fileName ) throws IOException,FileNotFoundException { 

            FileInputStream fin = new FileInputStream( fileName ); 

            BufferedReader input = new BufferedReader( new InputStreamReader( fin ) ); 

            StringBuffer buffer = new StringBuffer(); 

            String line = null; 

            while ( ( line = input.readLine() ) != null ) { 

            line = line.replaceAll( "\\s+", " " ); 

            buffer.append( line ); 

          } 

          fin.close(); 

          return buffer.toString(); 

        } // End of cat 

        public static void main( String[] args )throws IOException,FileNotFoundException { 
        if( args.length != 1){
            System.out.println("Usage: java L9 fileName ");
            System.exit(0);
        }
        try{
        System.out.println( cat( args[ 0 ] ) ); 
        }
        catch (FileNotFoundException e){
            System.err.println("File not found: "+e.getMessage());
        }
        catch(IOException  e){
            System.err.println( "Cannot read/write file: "+e.getMessage() );

        }
      } 
    }