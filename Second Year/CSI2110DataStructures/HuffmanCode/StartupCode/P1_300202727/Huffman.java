
import java.io.*;
import java.util.ArrayList;


import net.datastructures.*;

/**
 * Class Huffman that provides huffman compression encoding and decoding of files
 * @author Lucia Moura 2021
 *
 */

public class Huffman {

	/**
	 * 
	 * Inner class Huffman Node to Store a node of Huffman Tree
	 *
	 */
	private class HuffmanTreeNode { 
	    private int character;      // character being represented by this node (applicable to leaves)
	    private int count;          // frequency for the subtree rooted at node
	    private HuffmanTreeNode left;  // left/0  subtree (NULL if empty)
	    private HuffmanTreeNode right; // right/1 subtree subtree (NULL if empty)
	    public HuffmanTreeNode(int c, int ct, HuffmanTreeNode leftNode, HuffmanTreeNode rightNode) {
	    	character = c;
	    	count = ct;
	    	left = leftNode;
	    	right = rightNode;
	    }
	    public int getChar() { return character;}
	    public Integer getCount() { return count; }
	    public HuffmanTreeNode getLeft() { return left;}
	    public HuffmanTreeNode getRight() { return right;}
		public boolean isLeaf() { return left==null ; } // since huffman tree is full; if leaf=null so must be right
	}
	
	/**
	 * 
	 * Auxiliary class to write bits to an OutputStream
	 * Since files output one byte at a time, a buffer is used to group each output of 8-bits
	 * Method close should be invoked to flush half filed buckets by padding extra 0's
	 */
	private class OutBitStream {
		OutputStream out;
		int buffer;
		int buffCount;
		public OutBitStream(OutputStream output) { // associates this to an OutputStream
			out = output;
			buffer=0;
			buffCount=0;
		}
		public void writeBit(int i) throws IOException { // write one bit to Output Stream (using byte buffer)
		    buffer=buffer<<1;
		    buffer=buffer+i;
		    buffCount++;
		    if (buffCount==8) { 
		    	out.write(buffer); 
		    	//System.out.println("buffer="+buffer);
		    	buffCount=0;
		    	buffer=0;
		    }
		}
		
		public void close() throws IOException { // close output file, flushing half filled byte
			if (buffCount>0) { //flush the remaining bits by padding 0's
				buffer=buffer<<(8-buffCount);
				out.write(buffer);
			}
			out.close();
		}
		
 	}
	
	/**
	 * 
	 * Auxiliary class to read bits from a file
	 * Since we must read one byte at a time, a buffer is used to group each input of 8-bits
	 * 
	 */
	private class InBitStream {
		InputStream in;
		int buffer;    // stores a byte read from input stream
		int buffCount; // number of bits already read from buffer
		public InBitStream(InputStream input) { // associates this to an input stream
			in = input;
			buffer=0; 
			buffCount=8;
		}
		public int readBit() throws IOException { // read one bit to Output Stream (using byte buffer)
			if (buffCount==8) { // current buffer has already been read must bring next byte
				buffCount=0;
				buffer=in.read(); // read next byte
				if (buffer==-1) return -1; // indicates stream ended
			}
			int aux=128>>buffCount; // shifts 1000000 buffcount times so aux has a 1 is in position of bit to read
			//System.out.println("aux="+aux+"buffer="+buffer);
			buffCount++;
			if ((aux&buffer)>0) return 1; // this checks whether bit buffcount of buffer is 1
			else return 0;
			
		}

	}
	
	/**
	 * Builds a frequency table indicating the frequency of each character/byte in the input stream
	 * @param input is a file where to get the frequency of each character/byte
	 * @return freqTable a frequency table must be an ArrayList<Integer? such that freqTable.get(i) = number of times character i appears in file 
	 *                   and such that freqTable.get(256) = 1 (adding special character representing"end-of-file")
	 * @throws IOException indicating errors reading input stream
	 */
	
	private ArrayList<Integer> buildFrequencyTable(InputStream input) throws IOException{
		ArrayList<Integer> freqTable= new ArrayList<Integer>(257); // declare frequency table
		for (int i=0; i<257;i++) freqTable.add(i,0); // initialize frequency values with 0
		
		/************ your code comes here ************/
		
		int c =0;
		while((c=input.read())!=-1){
			freqTable.set(c, freqTable.get(c)+1);
		}
		
	
		freqTable.add(256,1);
        int ck=0;
	
		return freqTable; // return computer frequency table
	}

	/**
	 * Create Huffman tree using the given frequency table; the method requires a heap priority queue to run in O(nlogn) where n is the characters with nonzero frequency
	 * @param freqTable the frequency table for characters 0..255 plus 256 = "end-of-file" with same specs are return value of buildFrequencyTable
	 * @return root of the Huffman tree build by this method
	 */
	private HuffmanTreeNode buildEncodingTree(ArrayList<Integer> freqTable) {
		
		// creates new huffman tree using a priority queue based on the frequency at the root
		
		/************ your code comes here ************/
		Huffman main = new Huffman();
		HeapPriorityQueue<Integer,HuffmanTreeNode> p = new HeapPriorityQueue<Integer,HuffmanTreeNode>();
		for (int i = 0; i < freqTable.size(); i++) {
			if(i==256){
				HuffmanTreeNode t = main.new HuffmanTreeNode(256,1,null,null);
				p.insert(freqTable.get(i),t);
			}else{
			if(freqTable.get(i)!=0){
				HuffmanTreeNode t = main.new HuffmanTreeNode(i,(int)freqTable.get(i),null,null);
				p.insert(freqTable.get(i),t);
			}
		}
		}
		while(p.size()>1){
			Entry<Integer,HuffmanTreeNode> e1 = p.removeMin(), e2 =p.removeMin();
			HuffmanTreeNode T = main.new HuffmanTreeNode(-1,e1.getKey()+e2.getKey(),e1.getValue(),e2.getValue());
			p.insert(T.getCount(), T);
		}
		int c =0;
		Entry<Integer,HuffmanTreeNode> endcodingTree = p.removeMin();
		ArrayList<String> code = buildEncodingTable(endcodingTree.getValue());
		int a= 0;
	   return endcodingTree.getValue(); // dummy return value so code compiles
	}
	
	
	/**
	 * 
	 * @param encodingTreeRoot - input parameter storing the root of the HUffman tree
	 * @return an ArrayList<String> of length 257 where code.get(i) returns a String of 0-1 correspoding to each character in a Huffman tree
	 *                                                  code.get(i) returns null if i is not a leaf of the Huffman tree
	 */
	private ArrayList<String> buildEncodingTable(HuffmanTreeNode encodingTreeRoot) {
		ArrayList<String> code= new ArrayList<String>(257); 
		for (int i=0;i<257;i++) code.add(i,null);
		
		/************ your code comes here ************/
		String encoded="";
		inOrder(encodingTreeRoot,code);
		
		return code;
	}
	private void inOrder(HuffmanTreeNode encodingTreeRoot,ArrayList<String> codes) {
		String encoders="";
	    inOrder( encodingTreeRoot, encoders,codes );
   
   }

   private void inOrder( HuffmanTreeNode encodingTreeRoot, String encoder, ArrayList<String> codes) {

	   if ( encodingTreeRoot != null ) {
		   
		if(encodingTreeRoot.getChar()!=-1){
			if(encodingTreeRoot.getChar()==256){

				codes.set(256, encoder);
			}else{
				codes.set(encodingTreeRoot.getChar(), encoder);
				
			}
			encoder = encoder.substring(0, encoder.length()-1);	
		}
			
		   inOrder( encodingTreeRoot.left,encoder+="0",codes );
	       encoder = encoder.substring(0, encoder.length()-1);
			
		   inOrder( encodingTreeRoot.right,encoder+="1",codes );
		   encoder = encoder.substring(0, encoder.length()-1);
		   
	   }else{
		encoder = encoder.substring(0, encoder.length()-1);
	   }
	   
   }
	
	/**
	 * Encodes an input using encoding Table that stores the Huffman code for each character
	 * @param input - input parameter, a file to be encoded using Huffman encoding
	 * @param encodingTable - input parameter, a table containing the Huffman code for each character
	 * @param output - output paramter - file where the encoded bits will be written to.
	 * @throws IOException indicates I/O errors for input/output streams
	 */
	private void encodeData(InputStream input, ArrayList<String> encodingTable, OutputStream output) throws IOException {
		OutBitStream bitStream = new OutBitStream(output); // uses bitStream to output bit by bit
	   
		/************ your code comes here ************/
		  
		
		int c=0, inputBytes=0,outputBytes=0;
		

		while((c= input.read())!= -1){
			inputBytes++;
			char[] bits = encodingTable.get(c).toCharArray();
			for (char bit : bits) {
				if(bit == '0'){
					bitStream.writeBit(0);
					//System.out.print(0);
				}else{
					bitStream.writeBit(1);
					//System.out.print(1);
				}
				outputBytes++;
			}
		}
	
		 
		char[] bits = encodingTable.get(256).toCharArray();
		for (char bit : bits) {
			if(bit == '0'){
				bitStream.writeBit(0);
				//System.out.print(0);
				
			}else{
				bitStream.writeBit(1);
				//System.out.print(1);
			}
			outputBytes++;
		}
		
		/* for (int i = 0; i < encodingTable.size(); i++) {
			if(encodingTable.get(i)!=null){
				outputBytes++;
			}
		} */
		System.out.println("Number of Bytes in Input: "+inputBytes);
		System.out.println("Number of Bytes in Output: "+(int)Math.ceil(outputBytes/8)+"\n");
		bitStream.close();
	 } // close bit stream; flushing what is in the bit buffer to output file
	
	//deep copies tree
	private HuffmanTreeNode copy(HuffmanTreeNode root) {
        HuffmanTreeNode left = null;
        HuffmanTreeNode right = null;
        if (root.left != null) {
            left = copy(root.left);
        }
        if (root.right != null) {
            right = copy(root.right);
        }
        return new HuffmanTreeNode(root.getChar(),root.getCount(), left, right);
    }
	/**
	 * Decodes an encoded input using encoding tree, writing decoded file to output
	 * @param input  input parameter a stream where header has already been read from
	 * @param encodingTreeRoot input parameter contains the root of the Huffman tree
	 * @param output output parameter where the decoded bytes will be written to 
	 * @throws IOException indicates I/O errors for input/output streams
	 */
	private void decodeData(ObjectInputStream input, HuffmanTreeNode encodingTreeRoot, FileOutputStream output) throws IOException {
		
		InBitStream inputBitStream= new InBitStream(input); // associates a bit stream to read bits from file
		
		/************ your code comes here ************/ 
		HuffmanTreeNode root = copy( encodingTreeRoot);
		//OutBitStream bitStream = new OutBitStream(output);
		
		//System.out.println();
		
		int c=0,inputBytes = 0, outputBytes=0;
		boolean stop = false;
		while(stop ==false){
			c= inputBitStream.readBit();
			inputBytes++;
			//System.out.print(c);
		
			switch(c){
				case 0:
				root =root.getLeft();
				break;
				case 1:
				root = root.getRight();
				break;
			}
			if (root.getChar() == 256){

				stop = true;

				
			}else if(root.getChar()!=-1){
				//bitStream.writeBit();
				output.write(root.getChar());
				outputBytes++;
				root = copy( encodingTreeRoot);
			}
			
		}
		System.out.println("Number of Bytes in Input: "+(int)Math.ceil(inputBytes/8));
		System.out.println("Number of Bytes in Output: "+outputBytes+"\n");
		
		
	}
  
    private int getLeafCount(HuffmanTreeNode node)
    {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return 1;
        else
            return getLeafCount(node.left) + getLeafCount(node.right);
    }
	
	 

    
	/**
	 * Method that implements Huffman encoding on plain input into encoded output
	 * @param input - this is the file to be encoded (compressed)
	 * @param codedOutput - this is the Huffman encoded file corresponding to input
	 * @throws IOException indicates problems with input/output streams
	 */
	public void encode(String inputFileName, String outputFileName) throws IOException {
		System.out.println("\nEncoding "+inputFileName+ " " + outputFileName);
		
		// prepare input and output files streams
		FileInputStream input = new FileInputStream(inputFileName);
		FileInputStream copyinput = new FileInputStream(inputFileName); // create copy to read input twice
		FileOutputStream out = new FileOutputStream(outputFileName);
 		ObjectOutputStream codedOutput= new ObjectOutputStream(out); // use ObjectOutputStream to print objects to file
 		
		ArrayList<Integer> freqTable= buildFrequencyTable(input); // build frequencies from input
		System.out.println("FrequencyTable is="+freqTable);
		HuffmanTreeNode root= buildEncodingTree(freqTable); // build tree using frequencies
		ArrayList<String> codes= buildEncodingTable(root);  // buildcodes for each character in file
		System.out.println("EncodingTable is="+codes);
		codedOutput.writeObject(freqTable); //write header with frequency table
		encodeData(copyinput,codes,codedOutput); // write the Huffman encoding of each character in file
	}
	
    /**
     * Method that implements Huffman decoding on encoded input into a plain output
     * @param codedInput  - this is an file encoded (compressed) via the encode algorithm of this class 
     * @param output      - this is the output where we must write the decoded file  (should original encoded file)
     * @throws IOException - indicates problems with input/output streams
     * @throws ClassNotFoundException - handles case where the file does not contain correct object at header
     */
	public void decode (String inputFileName, String outputFileName) throws IOException, ClassNotFoundException {
		System.out.println("\nDecoding "+inputFileName+ " " + outputFileName);
		// prepare input and output file streams
		FileInputStream in = new FileInputStream(inputFileName);
 		ObjectInputStream codedInput= new ObjectInputStream(in);
 		FileOutputStream output = new FileOutputStream(outputFileName);
 		
		ArrayList<Integer> freqTable = (ArrayList<Integer>) codedInput.readObject(); //read header with frequency table
		System.out.println("FrequencyTable is="+freqTable);
		
		HuffmanTreeNode root= buildEncodingTree(freqTable);
		decodeData(codedInput, root, output);
	}
	
	
}
	
    