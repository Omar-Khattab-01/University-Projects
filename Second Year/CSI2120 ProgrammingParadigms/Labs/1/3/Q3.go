package main

import (
	"fmt"
)

type Person struct {
	lastName  string
	firstName string
	iD        int
}

func inPerson(p *Person, lastIdNumber int) (nextId int, err error) {
	fmt.Print("First Name: ")
	var _ string
	_, err = fmt.Scanln(&p.firstName)
	if err != nil {
		return
	}
	fmt.Print("Last Name: ")
	var _ string
	_, err = fmt.Scanln(&p.lastName)
	if err != nil {
		return
	}

	nextId = lastIdNumber + 1
	p.iD = nextId
	return
}
func printPerson(p Person) {
	fmt.Printf("First Name: %s\tLast Name: %s\tID: %d\n", p.firstName, p.lastName, p.iD)
}
func main() {
	nextId := 101
	for {
		var (
			p   Person
			err error
		)
		nextId, err = inPerson(&p, nextId)
		if err != nil {
			fmt.Println("Invalid entry ... exiting")
			break
		}
		printPerson(p)
	}
}
