import java.util.List;
import java.util.LinkedList;

public class Series {
    private String string;

    public Series(String string) {
        this.string = string;
    }

    public List<String> slices(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Slice size is too small.");
        }

        if (string.length() < num) {
            throw new IllegalArgumentException("Slice size is too big.");
        }

        List<String> slices = new LinkedList<>();
        String copy = new String(string);
        while (copy.length() >= num) {
            slices.add(copy.substring(0, num));
            copy = copy.substring(1);
        }
        return slices;
    }
}
