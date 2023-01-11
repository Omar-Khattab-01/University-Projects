public class Iterative {

	public static BitList complement( BitList in ) {

		
		// Your code here
        BitList newBitList;
        Iterator iter= in.iterator();
        String[] complement = new String[in.toString().length()];
        int index = in.toString().length()-1;
        while(iter.hasNext()){
            int Binary = iter.next();
            if( Binary == BitList.ONE){
                complement[index--] = "0";
            }
            else if (Binary== BitList.ZERO){
                complement[index--] = "1";
            }
            
        }
        String complemented="";
        for (int i = 0; i < complement.length; i++) {
            complemented+= complement[i];
        }
        newBitList = new BitList(complemented);
        return newBitList;
	}

	public static BitList or( BitList a, BitList b ) {

		// Your code here
        if(a.toString() == "" || b.toString() == "")
            throw new IllegalArgumentException("list can not be empty!");
        if(a.toString().length() != b.toString().length())
            throw new IllegalArgumentException("Lists have to be of the same size");
        BitList ored;
        String[] oredBits = new String[a.toString().length()];
        int index = a.toString().length()-1;
        Iterator iter1 = a.iterator();
        Iterator iter2 = b.iterator();
        while(iter1.hasNext()){
            int bit1 =iter1.next();
            int bit2 = iter2.next();
            if(bit1 == BitList.ONE || bit2 == BitList.ONE){
                oredBits[index--] = "1";
            }else{
                oredBits[index--] = "0";
            }
        }
        String aOrB="";
        for (int i = 0; i < oredBits.length; i++) {
            aOrB +=oredBits[i];
        }
        ored =new BitList(aOrB);
        return ored;
    
        }

	public static BitList and( BitList a, BitList b ) {

		// Your code here
        if(a.toString() == "" || b.toString() == "")
            throw new IllegalArgumentException("list can not be empty!");
        if(a.toString().length() != b.toString().length())
            throw new IllegalArgumentException("Lists have to be of the same size");
        BitList aned;
        String[] anedBits = new String[a.toString().length()];
        int index = a.toString().length()-1;
        Iterator iter1 = a.iterator();
        Iterator iter2 = b.iterator();
        while(iter1.hasNext()){
            int bit1 =iter1.next();
            int bit2 = iter2.next();
            if(bit1 == BitList.ONE && bit2 == BitList.ONE){
                anedBits[index--] = "1";
            }else{
                anedBits[index--] = "0";
            }
        }
        String aAndB="";
        for (int i = 0; i < anedBits.length; i++) {
            aAndB +=anedBits[i];
        }
        aned =new BitList(aAndB);
        return aned;
    }

	public static BitList xor( BitList a, BitList b ) {

		if(a.toString() == "" || b.toString() == "")
            throw new IllegalArgumentException("list can not be empty!");
        if(a.toString().length() != b.toString().length())
            throw new IllegalArgumentException("Lists have to be of the same size");
        BitList xNor;
        String[] xNoredBits = new String[a.toString().length()];
        int index = a.toString().length()-1;
        Iterator iter1 = a.iterator();
        Iterator iter2 = b.iterator();
        while(iter1.hasNext()){
            int bit1 =iter1.next();
            int bit2 = iter2.next();
            if( (bit1 == BitList.ONE && bit2 == BitList.ZERO) || (bit1 == BitList.ZERO && bit2 == BitList.ONE) ){
                xNoredBits[index--] = "1";
            }else{
                xNoredBits[index--] = "0";
            }
        }
        String aNorB="";
        for (int i = 0; i < xNoredBits.length; i++) {
            aNorB +=xNoredBits[i];
        }
        xNor =new BitList(aNorB);
        return xNor;
	}

}