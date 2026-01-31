package matching_brackets

is_balanced :: proc(input: string) -> bool {
	// Implement this procedure.
	stack := make([dynamic]rune)
	defer delete(stack)
	for ch in input {
		switch ch {
		case '(', '[', '{':
			append(&stack, ch)
		case ')':
			if !pop_expected_ch(&stack, '(') {return false}
		case ']':
			if !pop_expected_ch(&stack, '[') {return false}
		case '}':
			if !pop_expected_ch(&stack, '{') {return false}

		}
	}
	return len(stack) == 0
}

pop_expected_ch :: proc(stack: ^[dynamic]rune, expected: rune) -> (ok: bool) {
	last := len(stack)
	if last == 0 {
		return false
	}

	if stack[last - 1] == expected {
		unordered_remove(stack, last - 1)
		ok = true
	}
	return
}
