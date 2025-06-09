import java.util.Arrays;
import java.util.List;

/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/

public class Anagram {

    private String word;
    private String sorted;

    public Anagram(String word) {
        this.word = word;
        this.sorted = sortWord(word);
    }

    private String sortWord(String s) {
        s = s.toLowerCase().replaceAll("[^a-z]", "");
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<String> match(List<String> words) {
        return words.stream().filter(s -> this.filter(s)).toList();
    }

    private boolean filter(String s) {
        return !s.equalsIgnoreCase(word) && sortWord(s).equals(sorted);
    }

}
