package connect

import "fmt"

var hexDistance = [][2]int{
	{-1, 1},
	{1, -1},

	{0, -1},
	{0, 1},

	{-1, 0},
	{1, 0},
}

func neighboursIn(lines []string) func([2]int) [][2]int {
	return func(pos [2]int) [][2]int {
		neighbours := [][2]int{}
		for _, p := range hexDistance {
			x, y := pos[0]+p[0], pos[1]+p[1]
			if y >= 0 && y < len(lines) && x >= 0 && x < len(lines[y]) {
				neighbours = append(neighbours, [2]int{x, y})
			}
		}
		return neighbours
	}
}

// for simplicity I don't handle special case with runes, I only expect ' ', '.', 'X', 'O'
func dfs(lines []string, starts [][2]int, player byte) map[[2]int]struct{} {
	neighboursFn := neighboursIn(lines)

	seen := make(map[[2]int]struct{})
	stack := starts
	for _, pos := range starts {
		seen[pos] = struct{}{}
	}

	for len(stack) != 0 {
		x := stack[len(stack) - 1]
		stack = stack[0:len(stack) - 1]
		for _, pos := range neighboursFn(x) {
			if _, ok := seen[pos]; !ok && lines[pos[1]][pos[0]] == player {
				stack = append(stack, pos)
				seen[pos] = struct{}{}
			}
		}
	}
	return seen
}

func ResultOf(lines []string) (string, error) {
	if len(lines) == 0 {
		return "", nil
	}

	start_O, start_X := [][2]int{}, [][2]int{}

	fmt.Printf("\nstart O [from %s]\n", lines[0])
	for i, ch := range lines[0] {
		if ch == 'O' {
			fmt.Printf("pos [%d, %d]\n", i, 0)
			start_O = append(start_O, [2]int{i, 0})
		}
	} 

	fmt.Printf("\n\nstart X\n")
	for i, row := range lines {
		fmt.Printf("row[%s] := ", row)
		for j, ch := range row {
			if ch == 'O' {
				break
			}
			if ch == 'X' {
				fmt.Printf("pos [%d, %d]", j, i)
				start_X = append(start_X, [2]int{j, i})
				break
			}
		}
		fmt.Printf("\n")
	}
	fmt.Printf("-------------\n")

	reachable_for_O := dfs(lines, start_O, 'O')
	reachable_for_X := dfs(lines, start_X, 'X')

	last := len(lines) - 1
	for i, _ := range lines[last] {
		if _, ok := reachable_for_O[[2]int{i, last}]; ok {
			return "O", nil
		}
	}

	for i, row := range lines {
		if _, ok := reachable_for_X[[2]int{len(row) - 1, i}]; ok {
			return "X", nil
		}
	}

	return "", nil
}
