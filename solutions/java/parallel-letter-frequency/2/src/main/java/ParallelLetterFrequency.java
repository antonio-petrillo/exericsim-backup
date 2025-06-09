import java.util.Map;
import java.util.HashMap;

public class ParallelLetterFrequency {

    private Map<Integer, Integer> counts;

    public ParallelLetterFrequency(String letters) {
        counts = new HashMap<>();
        letters = letters.toLowerCase().replaceAll("[^a-z]", "");
        counts.chars()
            .parallel()
            .forEach(c -> {
                    synchronized(counts){
                        counts.put((int) c, counts.getOrDefault((int) c, 0) + 1);
                    }
                });
    }

    public Map<Integer, Integer> letterCounts() {
        return counts;
    }

}
