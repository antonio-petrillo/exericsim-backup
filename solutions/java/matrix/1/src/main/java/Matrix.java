public class Matrix {

    private int[][] matrix;
    // I also have a transposed matrix because if not the method getRow is O(1)
    // and get Column is O(n), which is quite strange in a matrix.
    private int[][] transposed;

    Matrix(String matrixAsString) {
        String[] rowsToParse = matrixAsString.split("\n");
        matrix = new int[rowsToParse.length][];
        int i = 0;
        for (var row : rowsToParse) {
            String[] values = row.split(" ");
            matrix[i] = new int[values.length];
            int j = 0;
            for (var value : values) {
                matrix[i][j] = Integer.parseInt(value);
                j++;
            }
            i++;
        }
        transposed = new int[matrix[0].length][matrix.length];
        for (i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
    }

    int[] getRow(int rowNumber) {
        return matrix[rowNumber - 1];
    }

    int[] getColumn(int columnNumber) {
        return transposed[columnNumber - 1];
    }

}
