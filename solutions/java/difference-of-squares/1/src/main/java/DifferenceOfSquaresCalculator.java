class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int squareOfSum = 0;
        for (int i = 1; i <= input; i++) {
            squareOfSum += i;
        }
        return squareOfSum * squareOfSum;
    }

    int computeSumOfSquaresTo(int input) {
        int sumOfSquare = 0;
        for (int i = 1; i <= input; i++) {
            sumOfSquare += i * i;
        }
        return sumOfSquare;
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }

}
