/**
 * This class enables calculating (weighted-average) entropy values for a set of
 * datasets
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class EntropyEvaluator {

	/**
	 * A static method that calculates the weighted-average entropy of a given set
	 * (array) of datasets. The assignment description provides a detailed
	 * explanation of this calculation. In particular, note that all logarithms are
	 * to base 2. For your convenience, we provide a log2 method. You can use this
	 * method wherever you need to take logarithms in this assignment.
	 * 
	 * @param partitions is the array of datasets to compute the entropy of
	 * @return Shannon's logarithmic entropy (to base 2) for the partitions
	 */
	public static double evaluate(DataSet[] partitions) {
		// WRITE YOUR CODE HERE!
		try {
            double gain = 0.00;
            double yesAndNo = 0.00;
            double totalYes = 0.00;
            double totalNo = 0.00;
            String[] uniqueAttributes = partitions[0].getUniqueAttributeValues(partitions[0].numAttributes - 1);
            for (DataSet element : partitions) {
                for (int j = 0; j < element.numRows; j++) {
					yesAndNo++;
                }
            }
            for (DataSet element : partitions) {
                double entropy = 0.00;
                double yes = 0;
                double no = 0;
                for (int j = 0; j < element.numRows; j++) {
                    if (element.getValueAt(j, element.numAttributes - 1).equals(uniqueAttributes[0])) {
                        yes++;
                    } else {
                        no++;
                    }
 
                }
                totalYes += yes;
                totalNo += no;
                double firstRational = yes / (yes + no);
                double secondRational = no / (yes + no);
                double firstLog = log2(firstRational);
                double secondLog = log2(secondRational);
 
                if (yes > 0 && no > 0) {
                    entropy += ((-1 * firstRational * firstLog) - (secondRational * secondLog));
                    gain += (entropy * ((no + yes) / yesAndNo));
                }
 
            }
            double rational1 = totalYes / (yesAndNo);
            double rational2 = totalNo / (yesAndNo);
            double log1 = log2(rational1);
            double log2 = log2(rational2);
            double entropyDataSet = ((-1 * rational1 * log1) - (rational2 * log2));
            return entropyDataSet - gain;
        } catch (Exception e) {
            System.out.println("There was a problem with the evaluate() method in EntropyEvaluator.java");
 
        }
        return 0.00;
    }

	/**
	 * Calculate base-2 logarithm for a given number
	 * 
	 * @param x is the number to take the logarithm of
	 * @return base-2 logarithm for x
	 */
	public static double log2(double x) {
		return (Math.log(x) / Math.log(2));
	}
}
