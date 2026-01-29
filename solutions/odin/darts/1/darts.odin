package darts

score :: proc(x, y: f64) -> (n: int) {
    dist := x * x + y * y

    switch {
    case dist <= 1.0: n = 10
    case dist <= 25.0: n = 5
    case dist <= 100.0: n = 1
    case: n = 0
    }

	return 
}
