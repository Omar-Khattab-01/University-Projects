public abstract class AbstractSeries implements Series {

    public double[] take(int k) {

        // implement the method
        double[] series = new double[k];
        for(int i =0 ;i<k;i++){
            series[i] = next();
        }
        return series;    
    }

   

}