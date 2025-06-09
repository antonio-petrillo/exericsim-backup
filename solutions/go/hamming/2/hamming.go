package hamming

import "errors"

func Distance(a, b string) (int, error) {
    distance := 0
    if len(a) != len(b) {
        return 0, errors.New("strands cannot be of different size")
    }
    for i := range a {
        if a[i] != b[i] {
            distance++
        }
    }
	return distance, nil
}
