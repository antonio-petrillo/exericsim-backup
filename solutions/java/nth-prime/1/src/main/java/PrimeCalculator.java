import java.util.Iterator;

public class PrimeCalculator {

    public int nth(int nth) {
        if (nth == 0)
            throw new IllegalArgumentException("there is no zeroth prime");

        if (nth == 1)
            return 2;

        var iter = new PrimeIterator();
        int prime = 2;
        for (; nth > 1; nth--) {
            prime = iter.next();
        }
        return prime;
    }

    private static class PrimeIterator implements Iterator<Integer> {
        int prime = 3;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            int curr = prime;

            do {
                prime += 2;
            } while(!isPrime(prime));

            return curr;
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;

        if ((n & 1) == 0) return n == 2;

        int limit = (int) Math.ceil(Math.sqrt(n));
        for (int div = 3; div <= limit; div += 2)
            if (n % div == 0)
                return false;

        return true;
    }

}
