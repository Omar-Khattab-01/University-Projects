import java.util.Random;

public class Customer {
    private int arrivalTime, initialNumberOfItems, NumberOfItems;
    final private int MAX_NUM_ITEMS = 100;

    public Customer(int Arrival){
        arrivalTime = Arrival;
        Random generator;  
        generator = new Random();  
 
        int numItems;  
        numItems = generator.nextInt(MAX_NUM_ITEMS-1)+1;
        initialNumberOfItems  = numItems;
        NumberOfItems = numItems;

    }
    
    public int getArrivalTime(){
        return arrivalTime;
    }
    public int getNumberOfItems(){
        return NumberOfItems;
    }
    public int getNumberOfServedItems(){
        return initialNumberOfItems - NumberOfItems;
    }

    public void serve(){
        NumberOfItems--;
    }
}
