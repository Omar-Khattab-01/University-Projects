package main

import (
	"fmt"
	"strings"
	"unicode"
)

func CaesarCipher(m string, shift int) (res string) {
	for _, c := range m {
		if unicode.IsLetter(c) {
			c := []rune(strings.ToUpper(string(c)))
			newUni := (c[0] + rune(shift))
			if newUni >= 90 {
				newUni = (newUni % 90) + 64
			} else {
				newUni = newUni % 90
			}
			res = res + string(newUni)
		}
	}
	return
}

func main() {
	c := CaesarCipher("I love cs!", 5)
	fmt.Println(c)

}
