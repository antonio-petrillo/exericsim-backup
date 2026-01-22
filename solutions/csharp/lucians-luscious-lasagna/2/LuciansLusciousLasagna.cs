public class Lasagna
{
    public int ExpectedMinutesInOven() => 40;

    public int RemainingMinutesInOven(int remaining) => ExpectedMinutesInOven() - remaining;

    public int PreparationTimeInMinutes(int layers) => layers << 1;
    
    public int ElapsedTimeInMinutes(int layers, int elapsed) => elapsed + PreparationTimeInMinutes(layers);
}
