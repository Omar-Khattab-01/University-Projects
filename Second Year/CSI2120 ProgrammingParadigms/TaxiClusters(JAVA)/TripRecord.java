public class TripRecord {
    private String pickip_DateTime;
    private GPScoord pickup_Loocation;
    private GPScoord dropoff_Loocation;
    private Status  status;
    private float trip_Distance;


    public TripRecord(String DateTime, GPScoord pickup, GPScoord dropoff, float distance){
        pickip_DateTime = DateTime;
        pickup_Loocation = pickup;
        dropoff_Loocation = dropoff;
        status = Status.NOTVISITED;
        trip_Distance = distance;
    }

    String getPickupDateTime(){
        return pickip_DateTime;
    }

    GPScoord getPickupLocation(){
        return pickup_Loocation;
    }
    GPScoord getDropoffLocation(){
        return dropoff_Loocation;
    }
    float getTripDistance(){
        return trip_Distance;
    }
    Status getStatus(){
        return status;
    }
    

    void setToVisited(){
        status = Status.VISITED;
    }
    
    void setNoise(){
        status= Status.NOISE;
    } 
    void setLeaf(){
        status= Status.LEAF;
    }  

    
}
