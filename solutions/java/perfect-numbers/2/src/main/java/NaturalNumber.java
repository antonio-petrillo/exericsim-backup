import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class NaturalNumber {

    private Integer number;

    public NaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.number = Integer.valueOf(number);
    }

    private List<Integer> getFactors() {
        return IntStream.range(1, number).filter(i -> number % i == 0).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);

    }

    public Classification getClassification() {
        int aliquotSum = getFactors().stream().reduce(0, (x, y) -> x + y);
        int compareResult = number.compareTo(aliquotSum);
        if (compareResult == 0) {
            return Classification.PERFECT;
        } else if (compareResult == 1) {
            return Classification.DEFICIENT;
        } else {
            return Classification.ABUNDANT;
        }
    }

}
