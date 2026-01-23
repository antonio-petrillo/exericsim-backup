import java.util.stream.IntStream;

public class LargestSeriesProductCalculator {
    private final String input;

    public LargestSeriesProductCalculator(String inputNumber) {
        if (inputNumber.matches(".*\\D.*"))
            throw new IllegalArgumentException("String to search may only contain digits.");
        input = inputNumber;
    }

    public long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits < 0)
            throw new IllegalArgumentException("Series length must be non-negative.");

        if (numberOfDigits > input.length())
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");

        final var length = input.length();
        return IntStream.range(0, length)
            .mapToLong(start -> {
                    final int end = start + numberOfDigits;
                    if (end > length) return 0;
                    final var s = input.substring(start, end);
                    if (s.isEmpty()) return 0;
                    return product(s);
                })
            .max()
            .orElse(0);
    }

    private static long product(String s) {
        long product = 1;
        for (var ch : s.toCharArray()) {
            product *= ch - '0';
        }
        return product;
    }

}
