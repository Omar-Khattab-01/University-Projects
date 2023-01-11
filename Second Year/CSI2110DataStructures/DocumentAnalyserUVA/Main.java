import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException,IOException  {
        //if(args.length==2){
          //  getRange(args[0],args[1]);
        //}
        //getRange("TestDocAnalyserBig.txt", "a.txt");
        //FileInputStream input = new FileInputStream(inputFileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OutputStreamWriter output = new OutputStreamWriter(System.out);
        
        int numberOfFile = Integer.valueOf(br.readLine());
        boolean endFound = false;
        int doc = 1, wordCount=1, beg = 1,p=0,q=100000, rangeLen=0;
        HashMap<Integer,String> words;
        HashMap<String,Integer> docWords;


        String line,word="";
        
        for (int i = 0; i < numberOfFile; i++) {
            
            word = "";
            wordCount = 1;
            beg = 1;p=0;q=100000;rangeLen=0;
            words = new HashMap<>();
            docWords = new HashMap<>();
            while(!endFound){
                
                line = br.readLine(); 
                if(line.equals("END")){
                    //System.out.println(words.size());
                    endFound = true;
                    String currentWord;
                    int wordCounter=0,found=0;
                    wordCounter = docWords.size();
                    for (int j = 1; j < words.size()+1; j++) {
                        currentWord = words.get(j);
                        docWords.put(currentWord,docWords.get(currentWord)+1);
                        wordCount = docWords.get(currentWord);

                        if(wordCount == 1){
                            found++;
                        }
                        if(found == wordCounter){
                            while( docWords.get(words.get(beg))>1){
                                currentWord = words.get(beg);
                                docWords.put(currentWord, docWords.get(currentWord)-1);
                                beg++;
                            }

                            rangeLen = j - beg;
                            if(q>rangeLen){
                              
                                q= rangeLen;
                                p = beg;
                            } 
                        }

                    }
                    
                    
                    
                    System.out.println("Document "+doc++ +": "+p+" "+(p+q));
                    
                    output.write("Document "+(doc-1) +": "+p+" "+(p+q));                                 
                }else{
                    int charIndex=1;
                    char[] lineCharArr = line.toCharArray();
                    for (Character  letter : lineCharArr) {
                    
                        if(Character.isLetter(letter)){
                        
                            word+=letter;
                        }
                        if (!Character.isLetter(letter) || charIndex == line.length()){
                            if(!word.equals("")){
                            
                                words.put(wordCount++, word);
                                docWords.put(word, 0);
                                word = "";

                            }
                            
                        }
                        charIndex++;
                    }
                    
                }

            }
            endFound = false;
            if(i == 3 ){
                int x = 0;
                
            }
        }
            
            

            int s =0;
    }
        

}

