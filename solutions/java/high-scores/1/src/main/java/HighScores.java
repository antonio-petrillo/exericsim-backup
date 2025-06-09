import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;

class HighScores {

    private List<Integer> highScores;

    public HighScores(List<Integer> highScores) {
        this.highScores = highScores;
    }

    public List<Integer> scores() {
        return highScores;
    }

    public Integer latest() {
        return highScores.stream()
                .skip(highScores.size() - 1)
                .findFirst()
                .orElse(null);
    }

    public Integer personalBest() {
        return highScores.stream()
                .mapToInt(i -> i)
                .max()
                .orElse(0);
    }

    public List<Integer> personalTopThree() {
        return highScores.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
    }

}
