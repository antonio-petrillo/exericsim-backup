import java.util.Random;

public class CaptainsLog {

    private static final char[] PLANET_CLASSES = new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

    private Random random;

    public CaptainsLog(Random random) {
        this.random = random;
    }

    public char randomPlanetClass() {
        return PLANET_CLASSES[random.nextInt(PLANET_CLASSES.length)];
    }

    public String randomShipRegistryNumber() {
        return String.format("NCC-%d", random.nextInt(9000) + 1000);
    }

    public double randomStardate() {
        return random.nextDouble() * 1000.0 + 41000.0;
    }
}
