package brackets

func Bracket(input string) bool {
	stack := []rune{}
    for _, ch := range input {
        switch ch {
            case '(', '[', '{':
            	stack = append(stack, ch)
            case ')':
            	if l := len(stack); l == 0 || stack[l-1] != '(' {
                    return false
                }
            stack = stack[:len(stack)-1]
            case ']':
            	if l := len(stack); l == 0 || stack[l-1] != '[' {
                    return false
                }
            stack = stack[:len(stack)-1] 
            case '}':
            	if l := len(stack); l == 0 || stack[l-1] != '{' {
                    return false
                }
            stack = stack[:len(stack)-1]
        }
    }
    return len(stack) == 0
}
