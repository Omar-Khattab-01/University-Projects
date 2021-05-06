import java.util.Arrays;

/**
 * This class enables the calculation and sorting of information gain values
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class InformationGainCalculator {

	/**
	 * @param dataset is the dataset whose attributes we want to analyze and sort
	 *                according to information gain
	 * @return an array of GainInfoItem instances sorted in descending order of gain
	 *         value
	 */
	public static GainInfoItem[] calculateAndSortInformationGains(VirtualDataSet dataset) {
		GainInfoItem[] gains = new GainInfoItem[dataset.getNumberOfAttributes() - 1];

		for (int i = 0; i < dataset.getNumberOfAttributes() - 1; i++) {

			VirtualDataSet[] newDataSet = dataset.partitionByNominallAttribute(i);
			double entropy = EntropyEvaluator.evaluate(newDataSet);

			if (Util.isArrayNumeric(dataset.getUniqueAttributeValues(i))) {
				String splitOn = numericEntropyCalc(dataset);
				gains[i] = new GainInfoItem(dataset.getAttribute(i).getName(), AttributeType.NUMERIC, entropy, splitOn);
			} else {

				gains[i] = new GainInfoItem(dataset.getAttribute(i).getName(), AttributeType.NOMINAL, entropy, null);
			}

		}
		return gains;
	}

	private static String numericEntropyCalc(VirtualDataSet dataSet) {
		int numericAttribute = -1;
		double temp = 0;
		double maximum = 0;
		String returnNumber = "";
		for (int i = 0; i < dataSet.numAttributes; i++) {
			if (Util.isNumeric(dataSet.getValueAt(1, i))) {

				numericAttribute = i;
				break;
			}
		}

		String[] uniqueNum = new String[dataSet.getUniqueAttributeValues(numericAttribute).length];
		String stringArr = "";
		// for (int j = 0; j < dataSet.getNumberOfDatapoints(); j++) {
		// 	stringArr += dataSet.getValueAt(j, numericAttribute) + ",";

		// }
		//String[] numbers = stringArr.split(",");
		int count=0;
		for (int i = 0; i < dataSet.getNumberOfDatapoints()-1;i++) {
			if (dataSet.getValueAt(i, numericAttribute).equals(uniqueNum[count])){
				VirtualDataSet[] dataSet2 =  dataSet.partitionByNumericAttribute(numericAttribute, i);
				double gainNumber = EntropyEvaluator.evaluate(dataSet2);
				temp = gainNumber;
				if(gainNumber>=maximum){
					returnNumber = uniqueNum[i];
			}
			
			maximum = Math.max(maximum, gainNumber);
			count++;	
			}
			

		}

		return returnNumber;
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		// if (args == null || args.length == 0) {
		// System.out.println("Expected a file name as argument!");
		// System.out.println("Usage: java InformationGainCalculator <file name>");
		// return;
		// }

		String strFilename = "weather-numeric.csv";
		// args[0]
		ActualDataSet actual = new ActualDataSet(new CSVReader(strFilename));

		// System.out.println(actual);

		VirtualDataSet virtual = actual.toVirtual();

		System.out.println(virtual);

		GainInfoItem[] items = calculateAndSortInformationGains(virtual);

		// Print out the output
		System.out.println(
				" *** items represent (attribute name, information gain) in descending order of gain value ***");
		System.out.println();

		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}
}
