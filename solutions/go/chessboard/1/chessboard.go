package chessboard

type File []bool

type Chessboard map[string] File

func CountInFile(cb Chessboard, file string) (count int) {
    for _, tile := range cb[file] {
		if tile {
            count++
        }
    }
    return count
}


func CountInRank(cb Chessboard, rank int) (count int) {
	if rank >= 1 && rank <= 8 {
        for _, row := range cb {
            if row[rank - 1] {
                count++
            }
        }
    }
	return count
}

func CountAll(cb Chessboard) (count int) {
	for _, row := range cb {
        count += len(row)
    }
    return count
}

func CountOccupied(cb Chessboard) (count int) {
	for _, row := range cb {
        for _, tile := range row {
            if tile {
                count++
            }
        }
    }
    return count
}
