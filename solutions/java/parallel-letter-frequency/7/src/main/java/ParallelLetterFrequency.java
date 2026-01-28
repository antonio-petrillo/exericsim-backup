import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelLetterFrequency {
    private Map<Character, Integer> counts;

    public ParallelLetterFrequency(String[] input) {
        counts = Arrays.stream(input)
            .parallel()
            .flatMap(s -> s.toLowerCase()
                     .chars()
                     .filter(Character::isLetter)
                     .boxed())
            .collect(Collectors.groupingByConcurrent(ch -> Character.valueOf((char) ch.intValue()), Collectors.summingInt(_ -> 1)));
    }

    public Map<Character, Integer> countLetters() {
        return counts;
    }
}
