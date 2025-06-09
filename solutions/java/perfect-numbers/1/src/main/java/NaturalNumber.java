import java.util.LinkedList;
import java.util.stream.IntStream;

public class NaturalNumber {

    private Integer number;

    public NaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.number = Integer.valueOf(number);
    }

    private LinkedList<Integer> getFactors() {
        return IntStream.range(1, number).filter(i -> number % i == 0).collect(LinkedList::new, LinkedList::add,
                LinkedList::addAll);

    }

    private Integer getFactorsSum(LinkedList<Integer> factors) {
        return factors.stream().reduce(0, (x, y) -> x + y);
    }

    public Classification getClassification() {
        int compareResult = number.compareTo(getFactorsSum(getFactors()));
        if (compareResult == 0) {
            return Classification.PERFECT;
        } else if (compareResult == 1) {
            return Classification.DEFICIENT;
        } else {
            return Classification.ABUNDANT;
        }
    }

}
