public class CarsAssemble {

    private final int CAR_PER_HOUR = 221;

    public double productionRatePerHour(int speed) {
        return speed * CAR_PER_HOUR * getSuccesRate(speed);
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (productionRatePerHour(speed) / 60);
    }

    private double getSuccesRate(int speed) {
        double successRate = 0d;
        if (speed >= 1 && speed <= 4) {
            successRate = 1.0d;
        } else if (speed >= 5 && speed <= 8) {
            successRate = 0.9d;
        } else if (speed == 9) {
            successRate = 0.8d;
        } else { // successRate == 10
            successRate = 77 / 100.0d;
        }
        return successRate;
    }
}
