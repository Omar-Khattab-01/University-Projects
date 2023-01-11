package main

import (
	"fmt"
)

type dog struct {
	name   string
	race   string
	female bool
}

func main() {
	fido := dog{"Fido", "Poodle", false}
	fmt.Printf("Dog name : %s\n", fido.name)
	fido.rename("Cocotte")
	fmt.Printf("Dog name after running fido.rename(): %s\n", fido.name)
}

func (dogStruc *dog) rename(newName string) {
	dogStruc.name = newName

}
