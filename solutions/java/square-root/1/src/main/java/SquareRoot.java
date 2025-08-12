public class SquareRoot {
    public int squareRoot(int radicand) {
        return binarySearch(radicand, 0, radicand + 1);
    }

    private static int binarySearch(int radicand, int left, int right) {
        while(left != right - 1) {
            final int middle = (left + right) >> 1;

            if (middle * middle <= radicand)
                left = middle;
            else
                right = middle;
        }
        
        return left;
    }
}
