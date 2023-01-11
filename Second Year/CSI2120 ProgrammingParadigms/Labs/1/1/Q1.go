package main

import (
	"fmt"
	"math"
)

func main() {
	var x float64
	fmt.Scanf("%f", &x)
	fmt.Println(ceil_floor(x))
}

func ceil_floor(number float64) (int, int) {
	return int(math.Floor(number)), int(math.Ceil(number))
}
