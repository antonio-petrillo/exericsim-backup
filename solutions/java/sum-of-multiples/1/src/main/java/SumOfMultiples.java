import java.util.Set;
import java.util.HashSet;

class SumOfMultiples {
    private int upTo;
    private int[] set;

    public SumOfMultiples(int number, int[] set) {
        this.upTo = number;
        this.set = set;
    }

    int getSum() {
        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i < set.length; i++) {
            for (int number = 1; number < upTo; number++) {
                if (set[i] != 0 && number % set[i] == 0) {
                    unique.add(number);
                }
            }
        }
        return unique.stream().reduce(0, (a, b) -> a + b);
    }

}
