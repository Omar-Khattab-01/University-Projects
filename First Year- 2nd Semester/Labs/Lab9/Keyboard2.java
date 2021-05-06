// Reads keyboad input, displays the characters and corresponding Unicodes,
// stops when the end-of-stream is reached.

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Keyboard2 {
    public static void main( String[] args ) throws IOException {
        InputStreamReader in = new InputStreamReader( System.in );
        char[] buffer = new char[ 250 ];
        while ( in.read(buffer) != -1){
            //String str = new String(buffer);
            System.out.println(buffer);
            Arrays.fill( buffer, '\u0000' );
        }
        
    }
}

// > java Keyboard
// On Unix you must type control-d in order to send an eos (-1) to
// the program.