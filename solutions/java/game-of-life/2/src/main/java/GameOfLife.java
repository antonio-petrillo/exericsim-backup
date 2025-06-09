class GameOfLife {
    public int[][] tick(int[][] matrix){
        var nextState = new int[matrix.length][];

        for (int row = 0; row < matrix.length; row++) {
            var newRow = new int[matrix[row].length];
            for (int col = 0; col < matrix[row].length; col++) {
                int neighboursCounts = getNeighboursCount(matrix, row, col);
                newRow[col] = getNextCellStatus(matrix, row, col, neighboursCounts);
            }
            nextState[row] = newRow;
        }
        
        return nextState;
    }

    private static int getNextCellStatus(int[][] matrix, int row, int col, int neighbourCounts) {
        return matrix[row][col] == 1 && (neighbourCounts == 2 || neighbourCounts == 3) || matrix[row][col] == 0 && neighbourCounts == 3 ? 1 : 0; 
    }

    private static int getNeighboursCount(int[][] matrix, int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int rowNeighbour = row + i, colNeighbour = col + j;
                if(isInBound(matrix, rowNeighbour, colNeighbour)
                   && matrix[rowNeighbour][colNeighbour] != 0
                ) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isInBound(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
