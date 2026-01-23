import java.util.stream.IntStream;

public class LargestSeriesProductCalculator {
    private final char[] input;

    public LargestSeriesProductCalculator(String inputNumber) {
        if (inputNumber.matches(".*\\D.*"))
            throw new IllegalArgumentException("String to search may only contain digits.");
        input = inputNumber.toCharArray();
    }

    public long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits < 0)
            throw new IllegalArgumentException("Series length must be non-negative.");

        if (numberOfDigits > input.length)
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");

        final var length = input.length;
        return IntStream.range(0, length)
            .mapToLong(start -> {
                    final int end = start + numberOfDigits;
                    if (end > length) return 0;
                    return product(start, end);
                })
            .max()
            .orElse(0);
    }

    private long product(int start, int end) {
        long product = 1;
        for (; start < end; start++) {
            product *= input[start] - '0';
        }
        return product;
    }

}
