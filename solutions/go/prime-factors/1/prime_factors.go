package prime

func Factors(n int64) (result []int64) {
	var factor int64 = 3

	for n%2 == 0 {
		result = append(result, 2)
		n >>= 1
	}

	for n > 1 {
		if n%factor == 0 {
			result = append(result, factor)
			n /= factor
			continue
		}
		factor += 2
	}

	return result
}
