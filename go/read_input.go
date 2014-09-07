package main

import (
	"fmt"
)

func main() {
	var input *string = new(string)

	_, err := fmt.Scanln(input)
	if err != nil {
		fmt.Println(err)
	}

	fmt.Printf("Hello, world. Hello, %s\n", *input)
}
