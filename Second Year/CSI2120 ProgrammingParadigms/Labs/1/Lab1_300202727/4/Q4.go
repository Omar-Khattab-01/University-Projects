package main

import (
	"fmt"
)

// Define a suitable structure
type Course struct {
	studentsN int
	professor string
	avg       float64
}

func main() {
	// Create a dynamic map m
	m := make(map[string]Course)
	// Add the courses CSI2120 and CSI2110 to the map
	m["CSI2120"] = Course{186, "Lang", 79.5}
	m["CSI2110"] = Course{211, "Moura", 81.0}

	for k, v := range m {
		fmt.Printf("Course Code: %s\n", k)
		fmt.Printf("Number of students: %d\n", v.studentsN)
		fmt.Printf("Professor: %s\n", v.professor)
		fmt.Printf("Average: %f\n\n", v.avg)
	}
}
