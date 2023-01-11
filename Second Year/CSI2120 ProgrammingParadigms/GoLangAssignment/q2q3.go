package main

import (
	"fmt"
	"sync"
)

func main() {
	// List of messages
	messages := []string{"Csi2520", "Csi2120", "3 Paradigms",
		"Go is 1st", "Prolog is 2nd", "Scheme is 3rd", "uottawa.ca", "csi/elg/ceg/seg", "800 King Edward"}
	// Create channels???
	jobs := make(chan string)
	var mutex sync.WaitGroup
	mutex.Add(len(messages))
	// call go funtion
	go CaesarCipherList(messages[:len(messages)/3], 2, &mutex, jobs) // send channels???
	go CaesarCipherList(messages[len(messages)/3:len(messages)-len(messages)/3], 2, &mutex, jobs)
	go CaesarCipherList(messages[len(messages)-len(messages)/3:], 2, &mutex, jobs)
	// print results ???
	// add synchronisation ???
	for i := 0; i < len(messages); i++ {
		fmt.Println(<-jobs)

	}
	close(jobs)
	mutex.Wait()

}

func CaesarCipherList(list []string, shift int, done *sync.WaitGroup, jobs chan string) {
	for _, word := range list {
		jobs <- CaesarCipher(word, shift)
		done.Done()
	}
}
