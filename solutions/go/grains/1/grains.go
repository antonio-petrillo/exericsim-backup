package grains

import "errors"

var errInvalidInput = errors.New("number must be in range [1, 64]")

func Square(number int) (uint64, error) {
	if number < 1 || number > 64 {
        return 0, errInvalidInput
    }
    return 1 << (number - 1), nil
}

func Total() (total uint64) {
	for i := 1; i <= 64; i++ {
        sq, _ := Square(i)
        total |= sq
    }
    return
}
