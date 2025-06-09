import java.util.List;
import java.util.LinkedList;

public class Series {
    private String string;

    public Series(String string) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException("series cannot be empty");
        }
        this.string = string;
    }

    public List<String> slices(int num) {
        
        
        if (num <= 0) {
            throw new IllegalArgumentException("slice length cannot be negative or zero");
        }

        if (string.length() < num) {
            throw new IllegalArgumentException("slice length cannot be greater than series length");
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
