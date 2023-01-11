import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TaxiClusters {
    private ArrayList<TripRecord> trips;
    private ArrayList<Cluster> clusters;
    private double eps;
    private double minPts;
    private String[] resultFileHeader = new String[]{"Cluster ID", "longitude","latiude","Number of Points"};

    public TaxiClusters(String fileName, double eps,double minPts){
        this.eps = eps;
        this.minPts = minPts;
        clusters = new ArrayList<Cluster>();
        trips = buildTrips(fileName);
        clusters = buildClusters();
        writeToCSV(clusters);
    }

    private double getEPS(){
        return eps;
    }

    private double getMinPts(){
        return minPts;
    }

    /***
     * parses the csv file
     * @param fileName
     * @return an array of TripRecords
     */
    private ArrayList<TripRecord> buildTrips(String fileName){
        ArrayList<TripRecord> trips = new ArrayList<TripRecord>();
        BufferedReader br;
        String line = "";
        try {    
            br = new BufferedReader(new FileReader(fileName+".csv"));
            br.readLine();
            while ((line = br.readLine())!=null){
                String[] info = line.split(",");
                trips.add(new TripRecord(info[4], new GPScoord(Double.parseDouble(info[8]), Double.parseDouble(info[9])), new GPScoord(Double.parseDouble(info[12]), Double.parseDouble(info[13])), Float.parseFloat(info[7])));
            }
        }catch(IOException e){
            System.out.println(fileName+" not found!");
            
        }
        

        return trips;
    }
    /**
     * writes the output in a csv file
     * @param clusters
     */
    private void writeToCSV(ArrayList<Cluster> clusters){
        try{ 
        PrintWriter writer = new PrintWriter(new File("Result.csv"));
        StringBuilder build = new StringBuilder();
        for (String word: resultFileHeader) {
            build.append(word+",");
        }
        build.append("\r\n");
        for (Cluster cluster : clusters) {
            build.append(cluster.getId()+",");
            build.append(cluster.getLong()+",");
            build.append(cluster.getLat()+",");
            build.append(cluster.getNOP()+",\r\n");

        }
        writer.write(build.toString());
        writer.close();
    }
        catch (FileNotFoundException e) {
           
        }
        
    }

    /**
     * it analyse the trips and builds clusters with the specified eps and MinPts
     * using the DBSCAN algo from Reference: https://en.wikipedia.org/wiki/DBSCAN 
     * @return array of all clusters
     */
    private ArrayList<Cluster> buildClusters(){
        ArrayList<Cluster> clusters = new ArrayList<Cluster>();
        int id = 0;
        
        for (TripRecord current : trips) {
            if(current.getStatus()==Status.VISITED)continue;
            
            HashSet<TripRecord> neighbours = getNeigbours(current);
            if(neighbours.size() < getMinPts()){
                current.setNoise();
                continue;    
            }
            Cluster newCluster = new Cluster(++id);
            newCluster.addTrip(current);
            current.setToVisited();
            Iterator iter = neighbours.iterator();  
            while(iter.hasNext()){
            
                TripRecord trip = (TripRecord) iter.next();
                if(trip.getStatus()==Status.NOISE){
                    newCluster.addTrip(trip);  
                    trip.setLeaf();
                }
                if(trip.getStatus()==Status.VISITED)continue;
                if(trip.getStatus() == Status.NOTVISITED){
                    
                newCluster.addTrip(trip);
                HashSet<TripRecord> subNeighbours = getNeigbours(trip);
                if(subNeighbours.size() >= getMinPts()){
                    neighbours.addAll(subNeighbours);
                    iter = neighbours.iterator(); 

            
                }
                trip.setToVisited();
            }
               
            }
            newCluster.setCenter();
            clusters.add(newCluster);
            
        }
           
        
        return clusters;
    }
    /***
     * 
     * @param core
     * @return the neigbours of the core point withing the distance of an eps or less
     */
    private HashSet<TripRecord> getNeigbours(TripRecord core){
        HashSet<TripRecord> neigbhours = new HashSet<TripRecord>();
        for (TripRecord trip : trips) {
            if(trip!=core){
            if(getDistance(trip.getPickupLocation(), core.getPickupLocation()) <= getEPS()){
               neigbhours.add(trip); 
            }
        }
        }
        return neigbhours;
    }

    /***
     * 
     * @param x
     * @param y
     * @return the distance between two points
     */
    private double getDistance(GPScoord x, GPScoord y){
        double result = 0;
        result =Math.sqrt(((y.getLatiude() - x.getLatiude()) * (y.getLatiude() - x.getLatiude())) + ((y.getLongtiude() - x.getLongtiude()) * (y.getLongtiude() - x.getLongtiude())));
        return result;
    }
    
    public static void main(String[] args) {
     
        //TaxiClusters DBSCAN = new TaxiClusters("ok",1,2);    
        //int a = 0;
        //System.out.println(DBSCAN.clusters.size());

        try{
            TaxiClusters DBSCAN = new TaxiClusters(args[0],Double.parseDouble(args[1]),Double.parseDouble(args[2]));    
            //TaxiClusters DBSCAN = new TaxiClusters("yellow_tripdata_2009-01-15_1hour_clean",0.0001,5);    
            System.out.println("Done: Results outputed in Results.csv");
        }catch(Exception e){
            System.out.println("Please follow arguments order (Filename eps minPts)");
        }
     }
    
}
