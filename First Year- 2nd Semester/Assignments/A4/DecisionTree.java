import java.io.IOException;

/**
 * This class enables the construction of a decision tree
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */

public class DecisionTree {

	private static class Node<E> {
		E data;
		Node<E>[] children;

		Node(E data) {
			this.data = data;
		}
	}

	Node<VirtualDataSet> root;

	/**
	 * @param data is the training set (instance of ActualDataSet) over which a
	 *             decision tree is to be built
	 */
	public DecisionTree(ActualDataSet data) {
		root = new Node<VirtualDataSet>(data.toVirtual());
		build(root);
	}

	/**
	 * The recursive tree building function
	 * 
	 * @param node is the tree node for which a (sub)tree is to be built
	 */
	@SuppressWarnings("unchecked")
	private void build(Node<VirtualDataSet> node) {
		// WRITE YOUR CODE HERE!
		VirtualDataSet[] newDataSet =  null;
		boolean flag = false;
		double a_max;
		if (node == null) {
			throw new NullPointerException("Null parameter");
		}

		if ((node.data == null) || (node.data.numAttributes < 1) || (node.data.numRows < 1)) {
			throw new IllegalStateException("No target values exist");
		}

		if ((node.data.numAttributes == 1) || (node.data.getAttribute(node.data.numAttributes - 1).getValues().length == 1)) {
			return;
		}

		for (int i = 0; i < node.data.numAttributes - 1; i++) {
			if (node.data.getAttribute(i).getValues().length > 1) {
				flag = true;
			}
		}
		if (flag == false) {
			return;
		} else if (flag == true) {
			GainInfoItem[] items = InformationGainCalculator.calculateAndSortInformationGains(node.data);
			a_max = items[0].getGainValue();
			if (items[0].getAttributeType() == AttributeType.NOMINAL) {
				newDataSet = node.data.partitionByNominallAttribute(node.data.getAttributeIndex(items[0].getAttributeName()));
					} else if (items[0].getAttributeType() == AttributeType.NUMERIC) {
						int indexForSplitOn = node.data.getAttributeIndex(items[0].getAttributeName());
		
						Attribute att = node.data.getAttribute(indexForSplitOn);
		
						String[] values = att.getValues();
		
						int index = -1;
		
						for (int i = 0; i < values.length; i++) {
							if (values[i].equals(items[0].getSplitAt())) {
								index = i;
								break;
							}
						}
						newDataSet = node.data.partitionByNumericAttribute(node.data.getAttributeIndex(items[0].getAttributeName()), index);
					}
		
				node.children = (Node<VirtualDataSet>[]) new Node[newDataSet.length];
				for (int i = 0; i < node.children.length; i++) {
					node.children[i] = new Node(newDataSet[i]);
				}
				for (int i = 0; i < node.children.length; i++) {
					build(node.children[i]);
				}

			}
		}

	

	@Override
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * The recursive toString function
	 * 
	 * @param node        is the tree node for which an if-else representation is to
	 *                    be derived
	 * @param indentDepth is the number of indenting spaces to be added to the
	 *                    representation
	 * @return an if-else representation of node
	 */
	private String toString(Node<VirtualDataSet> node, int indentDepth) {
        String tree = "";
		if (node == root.children[0]) {
            System.out.println("*** Decision tree for " + node.data.getSourceDataSet().getSourceId() + " ***\n");
        }

        if (node.children != null) {
            for (int i = 0; i < node.children.length; i++) {
                if (i == 0) {
                    tree += createIndent(indentDepth) + "if (" + node.children[i].data.getCondition() + ") {"
                            + "\n";
                } else {
                    tree += createIndent(indentDepth) + "else if (" + node.children[i].data.getCondition()+ ") {" + "\n";
                }
                tree += toString(node.children[i], indentDepth + 1);
                if ((i == node.children.length - 1) && (node != root.children[root.children.length - 1])) {
                    tree += createIndent(indentDepth - 1) + "}\n";
                }
            }
        } else {
            tree = "";
            tree += createIndent(indentDepth + 1)
                    + node.data.attributes[node.data.attributes.length - 1].getName() + " = "
                    + node.data.getUniqueAttributeValues(node.data.attributes.length - 1)[0] + "\n";
					tree += createIndent(indentDepth - 1) + "}" + "\n";

        }
        return tree;
    }

	/**
	 * @param indentDepth is the depth of the indentation
	 * @return a string containing indentDepth spaces; the returned string (composed
	 *         of only spaces) will be used as a prefix by the recursive toString
	 *         method
	 */
	private static String createIndent(int indentDepth) {
		if (indentDepth <= 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < indentDepth; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		if (args == null )
			throw new NullPointerException("Expected a file name as argument! -> Usage: java DecisionTree <file name>");
		if(args.length == 0)
			throw new ArrayIndexOutOfBoundsException("Expected a file name as argument! -> Usage: java DecisionTree <file name>");

		String strFilename = args[0];
		
		try{
		ActualDataSet data = new ActualDataSet(new CSVReader(strFilename));

		DecisionTree dtree = new DecisionTree(data);

		System.out.println(dtree);
		}catch (IOException e){
			System.out.print("File Not Found!");
		}
	}
}