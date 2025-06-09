package collatzconjecture

import "errors"

func CollatzConjecture(n int) (int, error) {
	if n <= 0 {
        return 0, errors.New("n must be strictly greater than 0 (n > 0)")
    }

    steps := 0

    for n != 1 {
        steps++
        if n & 1 == 0 {
            n = n >> 1
        } else {
            n = 3 * n + 1
        }
    }
    
    return steps, nil
}
