package main

import (
	"fmt"
	"math/rand"
	"time"
)

func TestFunc(c chan int) {
	wait := rand.Int() % 1000
	time.Sleep(time.Duration(wait) * time.Millisecond)
	c <- wait
}

func main() {
	rand.Seed(time.Now().UTC().UnixNano())

	for i := 0; i < 10; i++ {
		Wait()
	}
}

func Wait() {

	c := make(chan int)

	go TestFunc(c)

	select {
	case waited := <-c:
		fmt.Println("WAITED", waited)
	case <-time.After(500 * time.Millisecond):
		fmt.Println("TIMEOUT")
	}
}
