import java.util.List;
import java.util.BitSet;

public class Sieve {
    private final List<Integer> primes;

    public Sieve(int maxPrime) {
        primes = sieve(maxPrime);
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    private final List<Integer> sieve(int limit) {
        var bitSet = new BitSet();
        bitSet.set(2, limit + 1);

        for (int i = 2; i <= limit; i++) {
            if (bitSet.get(i)) {
                for (int j = i << 1; j <= limit; j += i) {
                    bitSet.clear(j);
                }
            }
        }

        return bitSet.stream()
            .boxed()
            .toList();
    }
}
