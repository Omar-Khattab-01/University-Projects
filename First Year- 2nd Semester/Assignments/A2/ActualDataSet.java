/**
 * This class is used for representing an actual dataset, that is, a dataset
 * that holds a data matrix
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class ActualDataSet extends DataSet {
	/**
	 * The data matrix
	 */
	private String[][] matrix;

	/**
	 * The source identifier for the data. When the data source is a file, sourceId
	 * will be the name and location of the source file
	 */
	private String dataSourceId;

	/**
	 * Constructor for ActualDataSet. In addition to initializing dataSourceId,
	 * numAttributes, numRows and matrix, the constructor needs to create an array of
	 * attributes (instance of the Attribute class) and initialize the "attributes"
	 * instance variable of DataSet.
	 * 
	 * 
	 * @param reader is the DataReader instance to read data from.
	 */
	public ActualDataSet(DataReader reader) {
		// WRITE YOUR CODE HERE!
		Boolean flag = false;
		dataSourceId = reader.getSourceId();
		numAttributes = reader.getNumberOfColumns();
		numRows = reader.getNumberOfDataRows();
		this.matrix = reader.getData();
		String[] names = reader.getAttributeNames();
		Attribute[] attArr = new Attribute[numAttributes];
		for (int i = 0; i < numAttributes; i++){ 
			flag = false;
			String[] values = getUniqueAttributeValues(i);
            AttributeType type = AttributeType.NOMINAL;
            for (int j = 0; j < values.length; j++) {
                if(Util.isNumeric(values[j])){
                    if(!flag){
						type = AttributeType.NUMERIC;
                }
            }else{
                flag = true;
                type = AttributeType.NOMINAL;
            }
        }
            attArr[i] = new Attribute(names[i], i, type, values);
			
		}
		attributes = attArr;
	}

	/**
	 * Implementation of DataSet's abstract getValueAt method for an actual dataset
	 */
	public String getValueAt(int row, int attributeIndex) {
		// WRITE YOUR CODE HERE!

		//Remove the following line when this method has been implemented
		try{
		return matrix[row][attributeIndex];
		}catch (Exception e){
			return null;
		}
	}


	/**
	 * @return the sourceId of the dataset.
	 */
	public String getSourceId() {
		// WRITE YOUR CODE HERE!
		
		//Remove the following line when this method has been implemented
		return this.dataSourceId;
	}

	/**
	 * Returns a virtual dataset over this (actual) dataset
	 * 
	 * @return a virtual dataset spanning the entire data in this (actual) dataset
	 */
	public VirtualDataSet toVirtual() {
		// WRITE YOUR CODE HERE!
		int[] rows = new int[getNumberOfDatapoints()];
		for (int i=0 ; i<rows.length ; i++){
			rows[i] = i;
		}
		
		VirtualDataSet vDataSet = new VirtualDataSet(this, rows, attributes);
		//Remove the following line when this method has been implemented
		return vDataSet;
	}

	/**
	 * Override of toString() in DataSet
	 * 
	 * @return a string representation of this (actual) dataset.
	 */
	public String toString() {
		// WRITE YOUR CODE HERE!
		
		//Remove the following line when this method has been implemented
		return "Actual dataset ("+getSourceId()+") with "+numAttributes+" attribute(s) and "+numRows+" row(s)" + super.toString();
	}
}