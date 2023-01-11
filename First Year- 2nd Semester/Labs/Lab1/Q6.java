import java.util.Scanner;
public class Q6{
	public static void main(String[] args){

		//your code here
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 10 grades sepreated by spaces: ");
		String[] grades = input.nextLine().split(" ",10);
		double[] gradesParsed = new double[10];
		int counter=0;
		for (String i:grades){
			gradesParsed[counter]=Double.valueOf(grades[counter]);
			counter++;
		}
		System.out.println("The average grade is "+calculateAverage(gradesParsed));
		System.out.println("The median is "+calculateMedian(gradesParsed));
		System.out.println("Number of failings are "+calculateNumberFailed(gradesParsed));
		System.out.println("Number of passes are "+calculateNumberPassed(gradesParsed));


	}

	public static double calculateAverage(double[] notes){
		//your code here
		double average=0.0;
		for(double i:notes){
			average= average+i;
		}
		return average/Double.valueOf(notes.length);
	}

	public static double calculateMedian(double[] notes){
		//your code here
		double median=0.0;
		int i, j, argMin;
		double tmp;

		for (i = 0; i < notes.length - 1; i++) {
			argMin = i;
			for (j = i + 1; j < notes.length; j++) {
				if (notes[j] < notes[argMin]) {
					argMin = j;
				}
			}

			tmp = notes[argMin];
			notes[argMin] = notes[i];
			notes[i] = tmp;
		}
		if (notes.length % 2==0){
			int middle = notes.length/2;
			return (notes[middle-1]+notes[middle])/2;
		}else{
			return (notes[notes.length/2]);
		}

	}

	public static int calculateNumberFailed(double[] notes){
		//your code here
		int numFailed = 0;
		for (int i = 0; i<notes.length;i++){
			if (notes[i] <50.0){
				numFailed++;
			}
		}
		return numFailed;
	}

	public static int calculateNumberPassed(double[] notes){
		//your code here
		int numPassed = 0;
		for (int i = 0; i<notes.length;i++){
			if (notes[i] >=50.0){
				numPassed++;
			}
		}
		return numPassed;
	}

}