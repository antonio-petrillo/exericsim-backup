package hamming

import "errors"

func Distance(a, b string) (int, error) {
    distance := 0
    if len(a) != len(b) {
        return 0, errors.New("strands cannot be of different size or empty")
    }
    bRune := []rune(b)
    for i, x := range a {
        if x != bRune[i] {
            distance++
        }
    }
	return distance, nil
}
