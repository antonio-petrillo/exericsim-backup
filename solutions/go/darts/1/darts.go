package darts

func Score(x, y float64) int {
	dist := x*x + y*y
	if dist <= 1 {
		return 10
	} else if dist <= 25 {
		return 5
	} else if dist <= 100 {
		return 1
	} else {
		return 0
	}
}
