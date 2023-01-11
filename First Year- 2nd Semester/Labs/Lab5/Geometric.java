public class Geometric extends AbstractSeries {

    // instance variables
    private int counter =0;
    private double valueOfI;
    private double aPowB;
    public double next() {

        // implement the method
        if(counter == 0){
            valueOfI = 1;
            counter++;
            return valueOfI;
        }
        aPowB = 1/(Math.pow(2.00, counter));
        valueOfI = valueOfI +aPowB;
        counter++;
        return valueOfI;
    }
}