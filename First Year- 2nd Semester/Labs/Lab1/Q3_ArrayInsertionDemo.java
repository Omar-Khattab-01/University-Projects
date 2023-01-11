public class Q3_ArrayInsertionDemo{

	public static int[] insertIntoArray(int[] beforeArray, int indexToInsert, int valueToInsert){
		// Your code here
		int beforeSize = beforeArray.length;
		int afterSize =beforeSize+1;
		int beforeCounter=0;
		int[] afterArray = new int[afterSize];
		for (int i =0; i<afterSize;i++){
			if (i==indexToInsert){
				afterArray[i]= valueToInsert;
			}else{
			afterArray[i]= beforeArray[beforeCounter];
			beforeCounter++;
			}
		}
		return afterArray;
	}

	public static void main(String[] args){
		// Your code here
		int[] before = new int[]{1,2,3,4,5};
		int[] after = insertIntoArray(before, 3, 15);
		System.out.println("Array before insertion:");
		for (int i:before){
			System.out.println(i);
		}
		System.out.println("Array after insertion of 15 at position 3:");
		for (int i:after){
			System.out.println(i);
		}
	}
}