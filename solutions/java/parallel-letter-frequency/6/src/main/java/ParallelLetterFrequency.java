import java.util.Map;

import java.util.HashMap;

public class ParallelLetterFrequency {

    private final Map<Integer, Integer> counts;

// gradle test  0.85s user 0.07s system 103% cpu 0.893 total
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

// gradle test  0.86s user 0.07s system 104% cpu 0.888 total
    public ParallelLetterFrequency(String letters) {
        counts = new HashMap<>();
        letters.chars()
            .parallel()
            .forEach(c -> {
                    if(validChar(c)){
                        c = Character.toLowerCase((char) c);
                        synchronized(counts){
                            counts.put((int) c, counts.getOrDefault((int) c, 0) + 1);
                        }
                    }
                });
    }

//gradle test  0.84s user 0.07s system 100% cpu 0.905 total

    // public ParallelLetterFrequency(String letters) {
    //     this.counts = new HashMap<>();
    //     letters = letters.toLowerCase().replaceAll("[^a-z]", "");
    //     int[] counts = new int[26];
    //     Object[] locks = new Object[26];
    //     for (int i = 0; i < 26; i++){
    //         locks[i] = new Object();
    //     }
    //     letters.chars()
    //         .parallel()
    //         .forEach(c -> {
    //                 synchronized (locks[(int)c - 97]) {
    //                     counts[(int) c - 97]++;
    //                 }
    //             });
    //     int index = 97;
    //     for (var c : counts) {
    //         if (c != 0) {
    //             this.counts.put(index++, c);
    //         }
    //     }
    // }

    private boolean validChar(int c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

// gradle test  0.86s user 0.07s system 102% cpu 0.904 total
    // public ParallelLetterFrequency(String letters) {
    //     this.counts = new HashMap<>();
    //     int[] counts = new int[26];
    //     Object[] locks = new Object[26];
    //     for (int i = 0; i < 26; i++) {
    //         locks[i] = new Object();
    //     }
    //     letters.chars()
    //         .parallel()
    //         .forEach(c -> {
    //                 if (validChar(c)) {
    //                     c = Character.toLowerCase((char) c);
    //                     synchronized (locks[(int) (Character.toLowerCase(c)) - 97]) {
    //                         counts[(int) c - 97]++;
    //                     }
    //                 }

    //             });
    //     int index = 97;
    //     for (var c : counts) {
    //         if (c != 0) {
    //             this.counts.put(index++, c);
    //         }
    //     }
    // }

    public Map<Integer, Integer> letterCounts() {
        return counts;
    }

}
