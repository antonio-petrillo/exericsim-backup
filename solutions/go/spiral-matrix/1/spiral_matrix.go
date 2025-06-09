package spiralmatrix

const(
    north = iota
    east 
    south
    west
)

func SpiralMatrix(size int) [][]int {
	square := size * size
    matrix := make([][]int, size)
    for i := 0; i < size; i++ {
        matrix[i] = make([]int, size)
    }
    direction := east
    x, y := 0, 0
    for i := 1; i <= square; i++ {
        matrix[x][y] = i
        if direction == east && (y == size - 1 || matrix[x][y + 1] != 0) {
            direction = south
        }
        if direction == south && (x == size - 1 || matrix[x + 1][y] != 0) {
            direction = west
        }
        if direction == west && (y == 0 || matrix[x][y - 1] != 0)  {
            direction = north
        }
        if direction == north && (x == 0 || matrix[x-1][y] != 0) {
            direction = east
        }
        switch direction {
            case north:
            	x,y = x - 1, y
            case east:
            	x,y = x, y + 1
            case south:
            	x, y = x + 1, y
            case west:
            	x, y = x, y - 1
        }
    }
    return matrix
}
