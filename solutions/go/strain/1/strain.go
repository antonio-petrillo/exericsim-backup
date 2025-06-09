package strain

func Keep[T any](input []T, predicate func(t T) bool) (result []T) {
	for _, value := range input {
        if predicate(value) {
            result = append(result, value)
        }
    }
    return result
}

func Discard[T any](input []T, predicate func(t T) bool) []T {
    negate := func(t T) bool {
        return !predicate(t)
    }
    return Keep(input, negate)
}
