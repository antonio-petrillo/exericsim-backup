import java.util.Map;
import java.util.HashMap;

public class ParallelLetterFrequency {

    private Map<Integer, Integer> counts;

    // public ParallelLetterFrequency(String letters) {
    //     counts = new HashMap<>();
    //     letters = letters.toLowerCase().replaceAll("[^a-z]", "");
    //     letters.chars()
    //         .parallel()
    //         .forEach(c -> {
    //                 synchronized(counts){
    //                     counts.put((int) c, counts.getOrDefault((int) c, 0) + 1);
    //                 }
    //             });
    // }

    public ParallelLetterFrequency(String letters) {
        this.counts = new HashMap<>();
        letters = letters.toLowerCase().replaceAll("[^a-z]", "");
        int[] counts = new int[26];
        letters.chars()
            .parallel()
            .forEach(c -> {
                   counts[(int) c - 97]++;
                });
        int index = 97;
        for(var c : counts) {
            if(c != 0){
                this.counts.put(index++, c);
            }
        }
    }

    public Map<Integer, Integer> letterCounts() {
        return counts;
    }

}
