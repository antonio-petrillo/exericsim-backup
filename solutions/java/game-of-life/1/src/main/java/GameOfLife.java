class GameOfLife {
    public int[][] tick(int[][] matrix){
        var nextState = new int[matrix.length][];

        for (int row = 0; row < matrix.length; row++) {
            var newRow = new int[matrix[row].length];
            for (int col = 0; col < matrix[row].length; col++) {
                int neighboursCounts = getNeighboursCount(matrix, row, col);
                if (matrix[row][col] == 0 && neighboursCounts == 3 
                    || matrix[row][col] == 1 && (neighboursCounts == 2 || neighboursCounts == 3)) 
                {
                    newRow[col] = 1;
                } else {
                    newRow[col] = 0;
                }
            }
            nextState[row] = newRow;
        }
        
        return nextState;
    }

    private static int getNeighboursCount(int[][] matrix, int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int rowNeighbour = row + i, colNeighbour = col + j;
                if(inBound(matrix, rowNeighbour, colNeighbour)
                   && matrix[rowNeighbour][colNeighbour] != 0
                ) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean inBound(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
