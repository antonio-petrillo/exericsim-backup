package reverse

func Reverse(input string) string {
    rev := []rune(input)
    size := len(rev)
    for i := 0; i < size / 2; i++ {
        rev[i], rev[size - i - 1] = rev[size - i - 1], rev[i]
    }
	return string(rev)
}
