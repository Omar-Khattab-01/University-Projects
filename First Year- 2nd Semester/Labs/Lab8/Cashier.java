public class Cashier{

    
    
    //instance variables
    private int customersServed;
    private int totalCustomerWaitTime;
    private int totalItemsServed;
    private Customer currentCustomer;
    private Queue<Customer> queue;
    
    public Cashier(){
        queue=new ArrayQueue<Customer>();
        customersServed=0;
        totalCustomerWaitTime=0;
        totalItemsServed=0;
    }
    
    public void addCustomer(Customer c){
        queue.enqueue(c);
      
    }
    
    public int getQueueSize(){
        return queue.size();
    }
    
    public void serveCustomers(int currentTime){
        if(currentCustomer==null && queue.isEmpty()) return;
      
        if(currentCustomer==null){
        
          currentCustomer=queue.dequeue();
        
          totalCustomerWaitTime += currentTime -currentCustomer.getArrivalTime();
        
          customersServed++;
        }
    
      
        currentCustomer.serve();
        if(currentCustomer.getNumberOfItems()==0){
          totalItemsServed += currentCustomer.getNumberOfServedItems();
          currentCustomer=null;
        }
    }
         
    
    public int getTotalCustomerWaitTime(){
        return totalCustomerWaitTime;
    }
    
    public int getTotalCustomersServed(){
        return customersServed;
    }
    
    public int getTotalItemsServed(){
        return totalItemsServed;
    }
    
    public String toString(){
      
        StringBuffer results= new StringBuffer();
      
        results.append("The total number of customers served is: ");
        results.append(customersServed);
        results.append(System.lineSeparator());
      
        results.append("The average number of items per customer is: ");
        results.append(totalItemsServed/customersServed);
        results.append(System.lineSeparator());
      
        results.append("The average waiting time(seconds) is: ");
        results.append(totalCustomerWaitTime/customersServed);
        
      
        return results.toString();
    }
    

    
    }
    