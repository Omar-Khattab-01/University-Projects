import java.util.HashSet;
public class Cluster {
    private HashSet<TripRecord> trips;
    private double longitude;
	private double latitiude;
	private double longAvg;
	private double latAvg;
    private int id, numberOfPoints;

    public Cluster(int id) {
		this.id = id;
		longAvg = 0;
		latAvg = 0;
		this.trips = new HashSet<TripRecord>();
		numberOfPoints = 0;
	}

    public HashSet<TripRecord> getTrips() {
		return trips;
	}
	
	public void addTrip(TripRecord trip) {
		trips.add(trip);
		longAvg+= trip.getPickupLocation().getLongtiude();
		latAvg+=trip.getPickupLocation().getLatiude();
		numberOfPoints++;
	}
	public void setCenter(){
		longitude = longAvg/trips.size();
		latitiude = latAvg/trips.size();
	}
 
	public double getLat() {
		return latitiude;
	}
	public double getLong() {
		return longitude;
	}
 
	public void setLat(double latitude) {
		this.latitiude = latitude;
	}
 
	public int getId() {
		return id;
	}
	
	public int getNOP(){
		return numberOfPoints;
	}
}
