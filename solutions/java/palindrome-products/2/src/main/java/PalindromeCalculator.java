import java.util.List;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class PalindromeCalculator {

    public SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int min, int max) {
        if (min < 0 || max < 0) {
            throw new IllegalArgumentException("invalid input: factors must be >= 0");
        }
        if (min > max) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }

        final var palindromes = new TreeMap<Long, List<List<Integer>>>();

        //I always have mixed feeling when using streams
        IntStream.rangeClosed(min, max)
            .parallel()
            .mapToObj(i -> IntStream.rangeClosed(i, max)
                      .parallel()
                      .mapToObj(j -> new Pair<>(Long.valueOf((long)i * j), List.<Integer>of(i, j)))
                      .filter(pair -> isPalindrome(pair.first))
                      .toList())
            .flatMap(id -> id.stream())
            .sequential()
            .forEach(pair -> palindromes
                     .computeIfAbsent(pair.first, _ -> new ArrayList<List<Integer>>())
                     .add(pair.second));

        palindromes.keySet()
            .stream()
            .sequential()
            .forEach(k -> palindromes.computeIfPresent(k, (_, v) -> List.copyOf(v)));

        return palindromes;
    }

    private static record Pair<S,T>(S first, T second) {}

    // I could've done a number to string and check if the string is palindrome, but with this I don't need to allocate objects
    /* Note that max int is 2^31 - 1 then the maximum that we can test is:
     * maxPalindrome = (2^31 - 1) * (2^31 - 1) = (2^31)^2 - 1^2 = 2^62 - 1
     * then maxPalindrome for sure fits in 64 bits given by 'long'.
     */
    private static boolean isPalindrome(long n) {
        if (n < 10) return true;
        long rev = 0;
        long num_digits = (long) Math.ceil(Math.log10(n));
        for (int i = 0; i < num_digits >> 1; i++) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        if ((num_digits & 1) == 1)
            n /= 10;
        return n == rev;
    }

}
