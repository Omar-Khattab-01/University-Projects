public class Q3_ReverseSortDemo {
	public static void main(String[] args){
		char[] unorderedLetters;
		unorderedLetters = new char[]{'b', 'm', 'z', 'a', 'u'};
		reverseSort(unorderedLetters);
		for (int i = 0 ; i < unorderedLetters.length; i++ )
			System.out.print(unorderedLetters[i]);
	}

	//method that sorts a char array into its reverse alphabetical order
	public static void reverseSort(char[] values){

		//your code here
		int i, j, maxIndex; 
		char temp;
		for (i =0; i<values.length;i++){
			maxIndex=i;
			for (j=i+1; j<values.length;j++){
				if (values[maxIndex]<values[j]){
					maxIndex =j;
				}
			}
		temp = values[maxIndex];
		values[maxIndex]=values[i];
		values[i]=temp;
		}
	}

}