import java.util.HashMap;
import java.util.Map;

/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/

public class NucleotideCounter {

    private String sequence;

    public NucleotideCounter(String sequence) {
        String sequenceValid = sequence.replaceAll("[^ACGT]", "");
        if (!sequence.equals(sequenceValid)) {
            throw new IllegalArgumentException("INVALID");
        }
        this.sequence = sequence;
    }

    public Map<Character, Integer> nucleotideCounts() {
        Map<Character, Integer> counts = new HashMap<>();
        counts.put('A', 0);
        counts.put('C', 0);
        counts.put('G', 0);
        counts.put('T', 0);

        for (char c : sequence.toCharArray()) {
            counts.merge(c, 1, (a, b) -> a + b);
        }

        return counts;
    }

}
