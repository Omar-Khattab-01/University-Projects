public class Arithmetic extends AbstractSeries {

    // instance variables
    private int counter = 1;
    private double valueOfI;
    public double next() {
        // implement the method
        
        if (counter ==1){
            valueOfI=(double)counter;
            counter++;
            return valueOfI;
            }
        valueOfI = valueOfI +counter;
        counter++;
        return valueOfI;
    }
    public static void main(String[] args){
        Arithmetic s = new Arithmetic();
        double[] actual = s.take(3);
        for (int i =0;i<actual.length ;i++){
            System.out.println(actual[i]);
        }
    }
}