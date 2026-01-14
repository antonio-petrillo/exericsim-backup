import java.util.Iterator;

public class Hamming {

    private final int difference;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        int difference = 0;
        for (int i = 0; i < leftStrand.length(); i++) {
            if (leftStrand.charAt(i) != rightStrand.charAt(i)) {
                difference++;
            }
        }
        this.difference = difference;
    }

    public int getHammingDistance() {
        return difference;
    }
}
