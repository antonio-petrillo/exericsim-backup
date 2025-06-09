import java.util.Map;

import java.util.HashMap;

public class ParallelLetterFrequency {

    private final Map<Integer, Integer> counts;


// gradle test  0.88s user 0.08s system 73% cpu 1.320 total
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


// gradle test  0.85s user 0.07s system 101% cpu 0.915 total
    public ParallelLetterFrequency(String letters) {
        this.counts = new HashMap<>();
        letters = letters.toLowerCase().replaceAll("[^a-z]", "");
        int[] counts = new int[26];
        Object[] locks = new Object[26];
        for (int i = 0; i < 26; i++){
            locks[i] = new Object();
        }
        letters.chars()
            .parallel()
            .forEach(c -> {
                    synchronized (locks[(int)c - 97]) {
                        counts[(int) c - 97]++;
                    }
                });
        int index = 97;
        for (var c : counts) {
            if (c != 0) {
                this.counts.put(index++, c);
            }
        }
    }

    public Map<Integer, Integer> letterCounts() {
        return counts;
    }

}
