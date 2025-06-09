package minesweeper

func Annotate(board []string) []string {
    annotated := []string{}
	for i, row := range board {
        new_row := ""
        for j, ch := range row {
            if ch == ' ' {
                count := count_bombs(i, j, board)
                new_row += digit_to_char(count)
            } else {
                new_row += "*"
            }
        }
        annotated = append(annotated, new_row)
    }
    return annotated
}

func digit_to_char(count int) string {
    switch(count) {
    case 1:
    	return "1"
    case 2:
    	return "2" 
	case 3:
    	return "3" 
    case 4:
    	return "4" 
    case 5:
    	return "5" 
    case 6:
    	return "6" 
    case 7:
    	return "7" 
    case 8:
    	return "8" 
    default:
    	return " "  
    }
}

func count_bombs(x, y int, board []string) (count int) {
    for i := -1; i < 2; i++ {
        for j := -1; j < 2; j++ {
            if i == 0 && j == 0 {
            	continue
            }
			if x + i >= 0 &&  x + i < len(board) && y + j >= 0 && y + j < len(board[x + i]) {
           		if board[x + i][y + j] == '*' {
                    count++
                }         
            }
            
        }
    }
    return count
}
