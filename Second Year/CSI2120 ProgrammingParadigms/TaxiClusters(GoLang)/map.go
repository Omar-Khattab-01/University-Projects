// Project CSI2120/CSI2520
// Winter 2022
// Robert Laganiere, uottawa.ca

package main

import (
	"encoding/csv"
	"fmt"
	"io"
	"math"
	"os"
	"runtime"
	"strconv"
	"sync"
	"time"
)

type GPScoord struct {
	lat  float64
	long float64
}

type LabelledGPScoord struct {
	GPScoord
	ID    int // point ID
	Label int // cluster ID
}
type Job struct {
	grid   []LabelledGPScoord
	minPts int
	eps    float64
	offset int
}

const N int = 20
const consumers int = 200
const MinPts int = 5
const eps float64 = 0.0003
const filename string = "yellow_tripdata_2009-01-15_9h_21h_clean.csv"

func main() {

	start := time.Now()

	gps, minPt, maxPt := readCSVFile(filename)
	fmt.Printf("Number of points: %d\n", len(gps))

	minPt = GPScoord{40.7, -74.}
	maxPt = GPScoord{40.8, -73.93}

	// geographical limits
	fmt.Printf("SW:(%f , %f)\n", minPt.lat, minPt.long)
	fmt.Printf("NE:(%f , %f) \n\n", maxPt.lat, maxPt.long)

	// Parallel DBSCAN STEP 1.
	incx := (maxPt.long - minPt.long) / float64(N)
	incy := (maxPt.lat - minPt.lat) / float64(N)

	var grid [N][N][]LabelledGPScoord // a grid of GPScoord slices

	// Create the partition
	// triple loop! not very efficient, but easier to understand

	partitionSize := 0
	for j := 0; j < N; j++ {
		for i := 0; i < N; i++ {

			for _, pt := range gps {

				// is it inside the expanded grid cell
				if (pt.long >= minPt.long+float64(i)*incx-eps) && (pt.long < minPt.long+float64(i+1)*incx+eps) && (pt.lat >= minPt.lat+float64(j)*incy-eps) && (pt.lat < minPt.lat+float64(j+1)*incy+eps) {

					grid[i][j] = append(grid[i][j], pt) // add the point to this slide
					partitionSize++
				}
			}
		}
	}

	// ***
	// This is the non-concurrent procedural version
	// It should be replaced by a producer thread that produces jobs (partition to be clustered)
	// And by consumer threads that clusters partitions

	jobs := make(chan Job, N*N)
	var mutex sync.WaitGroup

	mutex.Add(consumers)

	for i := 0; i < consumers; i++ {
		go consomme(jobs, &mutex)
	}
	for j := 0; j < N; j++ {
		for i := 0; i < N; i++ {
			//x := &[2]int{i, j}
			job := Job{grid[i][j], MinPts, eps, i*10000000 + j*1000000}
			jobs <- job
		}
	}
	close(jobs)

	mutex.Wait()

	// Parallel DBSCAN STEP 2.
	// Apply DBSCAN on each partition
	// ...

	// Parallel DBSCAN step 3.
	// merge clusters
	// *DO NOT PROGRAM THIS STEP

	end := time.Now()
	fmt.Printf("\nExecution time: %s of %d points\n", end.Sub(start), partitionSize)
	fmt.Printf("Number of CPUs: %d", runtime.NumCPU())
}
func consomme(jobs chan Job, done *sync.WaitGroup) {

	for {
		k, more := <-jobs

		if more {
			//fmt.Printf("%s consuming: %3d\n", name, k*k)
			DBscan(k.grid, k.minPts, k.eps, k.offset)

		} else {

			done.Done()
			return
		}
	}
}

// Applies DBSCAN algorithm on LabelledGPScoord points
// LabelledGPScoord: the slice of LabelledGPScoord points
// MinPts, eps: parameters for the DBSCAN algorithm
// offset: label of first cluster (also used to identify the cluster)
// returns number of clusters found
func DBscan(coords []LabelledGPScoord, MinPts int, eps float64, offset int) (nclusters int) {

	nclusters = 0
	for j := 0; j <= len(coords)-1; j++ {

		if coords[j].Label != 0 {
			continue
		}
		neighbours := coords[j].getNeigbhours(coords)
		if len(neighbours) < MinPts {
			coords[j].Label = -1
			continue
		}
		nclusters++
		coords[j].Label = nclusters + offset
		seedSet := neighbours
		for i := 0; i < len(seedSet); i++ {
			if seedSet[i].Label == -1 {
				seedSet[i].Label = nclusters + offset
			}
			if seedSet[i].Label != 0 {
				continue
			}
			seedSet[i].Label = nclusters + offset
			subNeigbhours := seedSet[i].getNeigbhours(coords)
			if len(subNeigbhours) >= MinPts {
				for i := 0; i < len(subNeigbhours); i++ {
					seedSet = append(seedSet, subNeigbhours[i])
				}

			}
		}
	}
	// End of DBscan function
	// Printing the result (do not remove)
	fmt.Printf("Partition %10d : [%4d,%6d]\n", offset, nclusters, len(coords))

	return nclusters
}

func (core LabelledGPScoord) getNeigbhours(trips []LabelledGPScoord) (neigbhors []*LabelledGPScoord) {
	neighbours := make([]*LabelledGPScoord, 0)
	for j := 0; j < len(trips); j++ {
		trip := trips[j]
		if trip.ID != core.ID && trip.getDistance(core) <= eps {
			neighbours = append(neighbours, &trips[j])
		}
	}
	return neighbours
}
func (x LabelledGPScoord) getDistance(y LabelledGPScoord) (result float64) {
	result = math.Sqrt(float64(((y.lat - x.lat) * (y.lat - x.lat)) + ((y.long - x.long) * (y.long - x.long))))
	return result
}

// reads a csv file of trip records and returns a slice of the LabelledGPScoord of the pickup locations
// and the minimum and maximum GPS coordinates
func readCSVFile(filename string) (coords []LabelledGPScoord, minPt GPScoord, maxPt GPScoord) {

	coords = make([]LabelledGPScoord, 0, 5000)

	// open csv file
	src, err := os.Open(filename)
	defer src.Close()
	if err != nil {
		panic("File not found...")
	}

	// read and skip first line
	r := csv.NewReader(src)
	record, err := r.Read()
	if err != nil {
		panic("Empty file...")
	}

	minPt.long = 1000000.
	minPt.lat = 1000000.
	maxPt.long = -1000000.
	maxPt.lat = -1000000.

	var n int = 0

	for {
		// read line
		record, err = r.Read()

		// end of file?
		if err == io.EOF {
			break
		}

		if err != nil {
			panic("Invalid file format...")
		}

		// get lattitude
		lat, err := strconv.ParseFloat(record[9], 64)
		if err != nil {
			panic("Data format error (lat)...")
		}

		// is corner point?
		if lat > maxPt.lat {
			maxPt.lat = lat
		}
		if lat < minPt.lat {
			minPt.lat = lat
		}

		// get longitude
		long, err := strconv.ParseFloat(record[8], 64)
		if err != nil {
			panic("Data format error (long)...")
		}

		// is corner point?
		if long > maxPt.long {
			maxPt.long = long
		}

		if long < minPt.long {
			minPt.long = long
		}

		// add point to the slice
		n++
		pt := GPScoord{lat, long}
		coords = append(coords, LabelledGPScoord{pt, n, 0})
	}

	return coords, minPt, maxPt
}
