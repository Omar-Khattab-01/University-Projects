package main

import (
	"fmt"
	"math"
	"math/rand"
)

type Series struct {
	a, b float64
}

func (s Series) add(t, TP int) float64 {
	return s.a*math.Sin(2.0*math.Pi*float64(t)/float64(TP)) + s.b*math.Cos(2.0*math.Pi*float64(t)/float64(TP))
}

func fourier(c [32]Series, t, TP int) (res float64) {
	res = c[0].a
	for n := 1; n < 32; n++ {
		res += c[n].add(t, TP)
	}
	return
}

//new function
func fourierSlice(c []Series, t, TP int) (res float64) {
	res = c[0].a
	for n := 1; n < len(c); n++ {
		res += c[n].add(t, TP)
	}
	return
}

func main() {
	TP := 4
	sz := 1
	var res float64

	// Enter size of fourier series
	fmt.Print("Size of series (1 ... 512): ")
	// Depending on your environment you may have to remove \n in the scanf
	_, err := fmt.Scanf("%d \n", &sz)
	for err != nil || sz < 1 || sz > 512 {
		fmt.Println("Must be positive integer (1...512).")
		fmt.Println("Size of series (1 ... 512): ")
		_, err = fmt.Scanf("%d \n", &sz)
	}
	fmt.Printf("Size: %d\n", sz)

	var c [32]Series

	for t := 0; t < TP; t++ {
		for k := 0; k < 32; k++ {
			c[k].a = rand.Float64()
			c[k].b = rand.Float64()
		}
		res += fourier(c, t, TP)
		fmt.Printf("%f ", res)
		fmt.Println()
	}

	c2 := make([]Series, sz)
	res = 0
	fmt.Println("\n \nnew output")

	for i := 0; i < TP; i++ {
		for k := 0; k < sz; k++ {
			c2[k].a = rand.Float64()
			c2[k].b = rand.Float64()
		}
		res += fourierSlice(c2, i, TP)
		fmt.Printf("%f", res)
		fmt.Println()

	}

}
