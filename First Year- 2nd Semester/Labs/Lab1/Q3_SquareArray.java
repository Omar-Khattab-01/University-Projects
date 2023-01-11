public class Q3_SquareArray{
	public static int[] createArray(int size){
		int[] indeces = new int[size];
		for (int i =0; i<indeces.length; i++){
			indeces[i]= i*i;
		}
		return indeces;
	}

	public static void main(String[] args){
		int[] squareArray = createArray(14);
		int index = 0;
		for (int item:squareArray){
			System.out.println("The Square of "+ index +" is: "+ item);
			index++;
		}
	}
}