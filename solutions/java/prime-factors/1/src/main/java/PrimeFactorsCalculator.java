import java.util.List;
import java.util.ArrayList;

public class PrimeFactorsCalculator {

    public List<Long> calculatePrimeFactorsOf(long number) {
        List<Long> result = new ArrayList<>();
        while ((number & 1) == 0) {
            number >>= 1;
            result.add(Long.valueOf(2));
        }
        long prime = 3;
        while(number != 1) {
            if (number % prime == 0) {
                number /= prime;
                result.add(Long.valueOf(prime));
            } else {
                prime += 2;
            }
        }
        return result;
    }

}
