package reverse

func Reverse(input string) string {
    rev := []rune(input)
    for i, j := 0, len(rev) - 1; i < j; i,j = i + 1, j - 1 {
        rev[i], rev[j] = rev[j], rev[i]
    }
	return string(rev)
}
