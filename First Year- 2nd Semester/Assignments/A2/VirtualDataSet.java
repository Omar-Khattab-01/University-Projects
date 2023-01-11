// You are allowed to use LinkedList or other Collection classes in A2 and A3
import java.util.LinkedList;

/**
 * This class is used for representing a virtual dataset, that is, a dataset
 * that is a view over an actual dataset. A virtual dataset has no data matrix
 * of its own.
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class VirtualDataSet extends DataSet {

	/**
	 * reference to the source dataset (instance of ActualDataSet)
	 */
	private ActualDataSet source;

	/**
	 * array of integers mapping the rows of this virtual dataset to the rows of its
	 * source (actual) dataset
	 */
	private int[] map;

	/**
	 * Constructor for VirtualDataSet. There are two important considerations here:
	 * (1) Make sure that you keep COPIES of the "rows" and "attributes" passed as
	 * formal parameters. Do not, for example, say this.map = rows. Instead, create
	 * a copy of rows before assigning that copy to this.map. (2) Prune the value
	 * sets of the attributes. Since a virtual dataset is only a subset of an actual
	 * dataset, it is likely that some or all of its attributes may have smaller
	 * value sets.
	 * 
	 * @param source     is the source dataset (always an instance of ActualDataSet)
	 * @param rows       is the set of rows from the source dataset that belong to
	 *                   this virtual dataset
	 * @param attributes is the set of attributes belonging to this virtual dataset.
	 *                   IMPORTANT: you need to recalculate the unique value sets
	 *                   for these attributes according to the rows. Why? Because
	 *                   this virtual set is only a subset of the source dataset and
	 *                   its attributes potentially have fewer unique values.
	 */
	public VirtualDataSet(ActualDataSet source, int[] rows, Attribute[] attributes) {
		// WRITE YOUR CODE HERE!
		this.source = source;
		int[] copRows = new int[rows.length];
		for(int i =0 ; i<rows.length;i++){
			copRows[i] = rows[i];
		}
		this.map = copRows;
		this.attributes = attributes.clone();
		super.numRows = this.map.length;
		super.numAttributes = this.attributes.length;
		
	}

	

	/**
	 * String representation of the virtual dataset.
	 */
	public String toString() {
		return "Virtual Dataset with " + super.numAttributes + " attribute(s) and "
				+ super.numRows + " row(s) \n - Dataset is a view over " + source.getSourceId()
				+ " \n - Row indices in this dataset (w.r.t. its source dataset) " + Util.intArrayToString(map) + "\n"
				+ super.toString();
	}

	/**
	 * Implementation of DataSet's getValueAt abstract method for virtual datasets.
	 * Hint: You need to call source.getValueAt(...). What you need to figure out is
	 * with what parameter values that method needs to be called.
	 */
	public String getValueAt(int row, int attributeIndex) {
		// WRITE YOUR CODE HERE!

		try{
		//Remove the following line when this method has been implemented
		return source.getValueAt(row,attributes[attributeIndex].getAbsoluteIndex());
	}catch (Exception e){return null;}
	}

	/**
	 * @return reference to source dataset
	 */
	public ActualDataSet getSourceDataSet() {
		// WRITE YOUR CODE HERE!
		
		//Remove the following line when this method has been implemented
		return this.source;

	}


	/**
	 * This method splits the virtual dataset over a nominal attribute. This process
	 * has been discussed and exemplified in detail in the assignment description.
	 * 
	 * @param attributeIndex is the index of the nominal attribute over which we
	 *                       want to split.
	 * @return a set (array) of partitions resulting from the split. The partitions
	 *         will no longer contain the attribute over which we performed the
	 *         split.
	 */
	public VirtualDataSet[] partitionByNominallAttribute(int attributeIndex) {
		// WRITE YOUR CODE HERE!

		try{
		int vDataSetSize = attributes[attributeIndex].getValues().length;
		String[] attVals = attributes[attributeIndex].getValues();
		int[] newRows = null ;


		VirtualDataSet[] vDataSet = new VirtualDataSet[vDataSetSize];
		int counter = 0;
		for (int i = 0; i<vDataSetSize;i++){
			String newMap ="";
			for(int j = 0; j<map.length;j++ ){
				if(getValueAt(map[j],attributeIndex).equals(attVals[counter])){
					if(newMap ==""){
					newMap = newMap+map[j];
				}else{
					newMap = newMap +","+map[j];
				}
				}
			}
			String[] newMapArr = newMap.split(",");
			int newCols = attributes.length -1;
			String[][] newMatrix = new String[newMapArr.length][newCols];
			int counter2 = 0;
			for (int k =0;k<newMapArr.length;k++){
				for(int j = 0; j<attributes.length;j++){
					if( j != attributeIndex ){
					newMatrix[k][counter2] = getValueAt(Integer.parseInt(newMapArr[k]),j);
					counter2++;
					}
				}
				counter2 = 0;
			}

			newRows = new int[newMapArr.length];

			for (int t = 0; t < newMapArr.length; t++) {
				newRows[t] = Integer.parseInt(newMapArr[t]);
			}
			
			boolean flag = false;
			Attribute[] attUniqueVals = new Attribute[attributes.length-1];
			for  (int l =0;l<attributes.length-1;l++){

				if ( l== attributeIndex){
					flag = true;
				}
				String[] uniqueVals = getUniqueAttributeValues(l,newMatrix);
				AttributeType type;
				if (Util.isArrayNumeric(uniqueVals)){
					type = AttributeType.NUMERIC;
				}else{
					type =AttributeType.NOMINAL;
				}
				if (flag){
				attUniqueVals[l] = new Attribute(attributes[l+1].getName(),l+1,type,uniqueVals);
				}else{
				attUniqueVals[l] = new Attribute(attributes[l].getName(),l,type,uniqueVals);
				}
				
			}
			vDataSet[i] = new VirtualDataSet(this.source,newRows,attUniqueVals);

			counter++;
		}
		//Remove the following line when this method has been implemented
		return vDataSet;
	}catch (Exception e){return null;}
	
	}

	private String[] getUniqueAttributeValues(int column, String[][] matrix) {

		try{
		String[] tempValues = new String[matrix.length];

		int count = 0;

		for (int i = 0; i < matrix.length; i++) {
			boolean found = false;

			for (int j = 0; j < count; j++) {
				if (matrix[i][column].equals(tempValues[j])) {
					found = true;
					break;
				}
			}

			if (!found) {
				tempValues[count++] = matrix[i][column];
			}
		}

		String[] values = new String[count];

		for (int i = 0; i < count; i++) {
			values[i] = tempValues[i];
		}

		return values;
	}catch (Exception e){return null;}
	}


	/**
	 * This method splits the virtual dataset over a given numeric attribute at a
	 * specific value from the value set of that attribute. This process has been
	 * discussed and exemplified in detail in the assignment description.
	 * 
	 * @param attributeIndex is the index of the numeric attribute over which we
	 *                       want to split.
	 * @param valueIndex     is the index of the value (in the value set of the
	 *                       attribute of interest) to use for splitting
	 * @return a pair of partitions (VirtualDataSet array of length two) resulting
	 *         from the two-way split. Note that the partitions will retain the
	 *         attribute over which we perform the split. This is in contrast to
	 *         splitting over a nominal, where the split attribute disappears from
	 *         the partitions.
	 */
	public VirtualDataSet[] partitionByNumericAttribute(int attributeIndex, int valueIndex) {
		// WRITE YOUR CODE HERE!
		try{
		int vDataSetSize = 2;
		int[] newRows = null;
		String[] vals = getUniqueAttributeValues(attributes[attributeIndex].getAbsoluteIndex()); 
		VirtualDataSet[] vDataSet = new VirtualDataSet[vDataSetSize];
		for (int i = 0; i<vDataSetSize;i++){
			String newMap ="";
			if(i==0){
			for(int j = 0; j<source.getNumberOfDatapoints();j++ ){
				if(Integer.parseInt(getValueAt(j,attributeIndex)) <= Integer.parseInt(vals[valueIndex])){
					if(newMap ==""){
					newMap = newMap+j;
				}else{
					newMap = newMap +","+j;
				}
				}
			}
		}else{
			for(int j = 0; j<source.getNumberOfDatapoints();j++ ){
				if(Integer.parseInt(getValueAt(j,attributeIndex)) > Integer.parseInt(vals[valueIndex])){
					if(newMap ==""){
					newMap = newMap+j;
				}else{
					newMap = newMap +","+j;
				}
				}
			}
		}
			String[] newMapArr = newMap.split(",");
			String[][] newMatrix = new String[newMapArr.length][attributes.length];
			for (int k =0;k<newMapArr.length;k++){
				for(int j = 0; j<attributes.length;j++){
					
					newMatrix[k][j] = getValueAt(Integer.parseInt(newMapArr[k]),j);

				}
			}

			newRows = new int[newMapArr.length];

			for (int t = 0; t < newMapArr.length; t++) {
				newRows[t] = Integer.parseInt(newMapArr[t]);
			}
			map = newRows;
			Attribute[] attUniqueVals = new Attribute[attributes.length];
			for  (int l =0;l<attributes.length;l++){

				
				String[] uniqueVals = getUniqueAttributeValues(l,newMatrix);
				AttributeType type;
				if (Util.isArrayNumeric(uniqueVals)){
					type = AttributeType.NUMERIC;
				}else{
					type =AttributeType.NOMINAL;
				}
				
				attUniqueVals[l] = new Attribute(attributes[l].getName(),l,type,uniqueVals);
				
				
			}
			vDataSet[i] = new VirtualDataSet(this.source,newRows,attUniqueVals);

		}
		//Remove the following line when this method has been implemented
		return vDataSet;
	}catch (Exception e){return null;}
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		System.out.println("============================================");
		System.out.println("THE WEATHER-NOMINAL DATASET:");
		System.out.println();

		ActualDataSet figure5Actual = new ActualDataSet(new CSVReader("weather-nominal.csv"));

		System.out.println(figure5Actual);

		VirtualDataSet figure5Virtual = figure5Actual.toVirtual();

		System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 5:");
		System.out.println();

		VirtualDataSet[] figure5Partitions = figure5Virtual
				.partitionByNominallAttribute(figure5Virtual.getAttributeIndex("outlook"));

		for (int i = 0; i < figure5Partitions.length; i++)
			System.out.println("Partition " + i + ": " + figure5Partitions[i]);
		
		System.out.println("============================================");
		System.out.println("THE WEATHER-NUMERIC DATASET:");
		System.out.println();

		ActualDataSet figure9Actual = new ActualDataSet(new CSVReader("weather-numeric.csv"));

		System.out.println(figure9Actual);

		VirtualDataSet figure9Virtual = figure9Actual.toVirtual();

		// Now let's figure out what is the index for humidity in figure9Virtual and
		// what is the index for "80" in the value set of humidity!

		int indexForHumidity = figure9Virtual.getAttributeIndex("humidity");

		Attribute humidity = figure9Virtual.getAttribute(indexForHumidity);

		String[] values = humidity.getValues();

		int indexFor80 = -1;

		for (int i = 0; i < values.length; i++) {
			if (values[i].equals("80")) {
				indexFor80 = i;
				break;
			}
		}

		if (indexFor80 == -1) {
			System.out.println("Houston, we have a problem!");
			return;
		}

		VirtualDataSet[] figure9Partitions = figure9Virtual.partitionByNumericAttribute(indexForHumidity, indexFor80);

		System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 9:");
		System.out.println();

		for (int i = 0; i < figure9Partitions.length; i++)
			System.out.println("Partition " + i + ": " + figure9Partitions[i]);


	}
}