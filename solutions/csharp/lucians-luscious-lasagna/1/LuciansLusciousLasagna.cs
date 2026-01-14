public class Lasagna
{
    public int ExpectedMinutesInOven() {
        return 40;
    }

    public int RemainingMinutesInOven(int remaining) {
        return ExpectedMinutesInOven() - remaining;
    }

    public int PreparationTimeInMinutes(int layers) {
        return layers << 1;
    }

    // TODO: define the 'ElapsedTimeInMinutes()' method
    public int ElapsedTimeInMinutes(int layers, int elapsed) {
        return elapsed + PreparationTimeInMinutes(layers);
    }
}
