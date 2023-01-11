import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Set;

public class Main {
    static char[][]graph;
    static FileInputStream input ;
    static OutputStreamWriter output;
    static BufferedReader br;
    static String line;
    static int graphXAxis=0,graphYAxis = 0,mijidXAxis = 0,mijidYAxis=0;
    static char discovered;
    static boolean[][] visitedLand;
    static char conqured;
    public static void main(String[] args) throws IOException {
        //input = new FileInputStream("TestContinentsBig.txt");
        br = new BufferedReader(new InputStreamReader(System.in));
        output = new OutputStreamWriter(System.out);

        line = "";        
        while((line = br.readLine())!= null){
            String[] axis = line.split(" ");
            graphXAxis = Integer.valueOf(axis[0]);
            graphYAxis = Integer.valueOf(axis[1]);
            graph  =new char[graphXAxis][graphYAxis];
            for (int i = 0; i < graphXAxis; i++) {
                char[] row = br.readLine().toCharArray();
                graph[i] = row;
                

            }
            visitedLand = new boolean[graphXAxis][graphYAxis];
            String[] mijidAxis = br.readLine().split(" ");
            mijidXAxis = Integer.valueOf(mijidAxis[0]);
            mijidYAxis = Integer.valueOf(mijidAxis[1]);

            
            
            
            conqured = graph[mijidXAxis][mijidYAxis];
            
            
			getContienentSize(mijidXAxis,mijidYAxis);
            int biggestContinent = 0;

            for (int i = 0; i < graphXAxis; i++) {
                for (int j = 0; j < graphYAxis; j++) {
                    if(graph[i][j]==conqured){
                    biggestContinent = Math.max(biggestContinent,getContienentSize(i,j));
                    }
                }
            }
            System.out.println(biggestContinent);
            output.write(biggestContinent);
            br.readLine();

        }
           
    }
    
   
        
    public static int getContienentSize (int xAxis, int yAxis) {
		if ( xAxis>=0 && xAxis<graphXAxis && yAxis>=0 && yAxis<graphYAxis && graph[xAxis][yAxis]==conqured  && !visitedLand[xAxis][yAxis]) {
            visitedLand[xAxis][yAxis]=true;
			int count=1;int add=0;
			for (int i=0;i<4;i++) {
                switch(i){
                    case 0 :
                    add=0;
                    break;
                    case 1:
                    add=-1;
                    break;
                    case 2:
                    add=1;
                    break;
                    case 3:
                    add=0;
                    break;
                    }
                     if (yAxis+add==-1) {
                        
                        switch(i){
                            case 0 :
                            count+=getContienentSize(xAxis-1,graphYAxis-1);
                            break;
                            case 1:
                            count+=getContienentSize(xAxis,graphYAxis-1);
                            break;
                            case 2:
                            count+=getContienentSize(xAxis,graphYAxis-1);
                            break;
                            case 3:
                            count+=getContienentSize(xAxis+1,graphYAxis-1);
                            break;
                            }
                    }
				else if (yAxis+add==graphYAxis) {
                    
                    switch(i){
                    case 0 :
                    count+=getContienentSize(xAxis-1,0);
                    break;
                    case 1:
                    count+=getContienentSize(xAxis,0);
                    break;
                    case 2:
                    count+=getContienentSize(xAxis,0);
                    break;
                    case 3:
                    count+=getContienentSize(xAxis+1,0);
                    break;
                    }
				}  else {
					
                    switch(i){
                        case 0 :
                        count+=getContienentSize(xAxis-1,yAxis);
                        break;
                        case 1:
                        count+=getContienentSize(xAxis,yAxis-1);
                        break;
                        case 2:
                        count+=getContienentSize(xAxis,yAxis+1);
                        break;
                        case 3:
                        count+=getContienentSize(xAxis+1,yAxis);
                        break;
                        }
                    
				}
			}
			return count;
		}
		return 0;
	}
}


