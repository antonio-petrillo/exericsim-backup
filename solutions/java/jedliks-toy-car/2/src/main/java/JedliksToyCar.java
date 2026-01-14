public class JedliksToyCar {
    private int battery;
    private int drivenMeters;

    public JedliksToyCar() {
        battery = 100;
        drivenMeters = 0;
    }

    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven " + drivenMeters + " meters";
    }

    public String batteryDisplay() {
        return battery > 0 ? "Battery at " + battery + "%" : "Battery empty";
    }

    public void drive() {
        if (battery > 0) {
            battery--;
            drivenMeters += 20;
        }
    }
}
