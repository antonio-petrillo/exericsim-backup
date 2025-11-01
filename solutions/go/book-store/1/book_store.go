package bookstore

import (
	"slices"
)

func Cost(books []int) int {
	var frequencies [5]int
	for _, book := range books {
		frequencies[book - 1]++
	}

	slices.Sort(frequencies[:])

	basket := [5]int{frequencies[0]}
	for i := 1; i < 5; i++ {
		basket[i] = frequencies[i] - frequencies[i - 1]
	}

	count := min(basket[0], basket[2])
	if count > 0 {
		basket[1] += count << 1
		basket[0] -= count 
		basket[2] -= count 
	}

	return basket[0] * 3000 + basket[1] * 2560 + basket[2] * 2160 + basket[3] * 1520  + basket[4] * 800
}
