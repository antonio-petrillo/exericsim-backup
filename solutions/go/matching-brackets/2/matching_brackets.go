package brackets

//go:inline
func matcher(ch rune) rune {
    switch ch {
        case ')':
        return '('
        case ']':
        return '['
        case '}':
        return '{'
        default:
        panic("invalid char")
    }
}

func Bracket(input string) bool {
	stack := []rune{}
    for _, ch := range input {
        switch ch {
            case '(', '[', '{':
            	stack = append(stack, ch)
            case ')', ']','}':
            	if l := len(stack); l == 0 || stack[l-1] != matcher(ch) {
                    return false
                }
            stack = stack[:len(stack)-1]
        }
    }
    return len(stack) == 0
}
