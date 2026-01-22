import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Sieve {
    private final List<Integer> primes;

    public Sieve(int maxPrime) {
        primes = sieve(maxPrime);
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    private final List<Integer> sieve(int limit) {
        return IntStream.rangeClosed(2, limit)
            .filter(Sieve::isPrime)
            .boxed()
            .toList();
    }

    private static final boolean isPrime(int num) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(num))
            .allMatch(n -> num % n != 0);
    }
}
