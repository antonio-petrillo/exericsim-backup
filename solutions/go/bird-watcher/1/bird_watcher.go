package birdwatcher

func TotalBirdCount(birdsPerDay []int) int {
	sum := 0
	for _, v := range birdsPerDay {
		sum += v
	}
	return sum
}

func BirdsInWeek(birdsPerDay []int, week int) int {
	// NOTE: there is no check/control if the week is 7 day long
	offset := (week - 1) * 7
	sum := 0
	for i := 0; i < 7; i++ {
		sum += birdsPerDay[offset+i]
	}
	return sum
}

func FixBirdCountLog(birdsPerDay []int) []int {
	for i, _ := range birdsPerDay {
		if i%2 == 0 {
			birdsPerDay[i]++
		}
	}
	return birdsPerDay
}
