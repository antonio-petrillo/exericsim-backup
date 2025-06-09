class NeedForSpeed {
    private final int BATTERY_FULL = 100;

    private int battery = BATTERY_FULL;
    private int batteryDrain;
    private int speed;
    private int distance = 0;

    public NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public boolean batteryDrained() {
        return battery <= 0;
    }

    public int distanceDriven() {
        return distance;
    }

    public void drive() {
        if (!batteryDrained()) {
            battery -= batteryDrain;
            distance += speed;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }
}

class RaceTrack {
    private int length;

    public RaceTrack(int length) {
        this.length = length;
    }

    public boolean tryFinishTrack(NeedForSpeed car) {
        while (car.distanceDriven() < length) {
            if (car.batteryDrained()) {
                return false;
            }
            car.drive();
        }
        return true;
    }
}
