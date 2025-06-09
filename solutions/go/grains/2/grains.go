package grains

import "errors"

var errInvalidInput = errors.New("number must be in range [1, 64]")
const maxUint64 = 0xffffffffffffffff 

func Square(number int) (uint64, error) {
	if number < 1 || number > 64 {
        return 0, errInvalidInput
    }
    return 1 << (number - 1), nil
}

func Total() uint64 {
    return maxUint64
}
