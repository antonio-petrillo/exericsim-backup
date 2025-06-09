import java.util.List;

public class TestTrack {

    public static void race(RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(ProductionRemoteControlCar prc1,
            ProductionRemoteControlCar prc2) {
        if (prc1.compareTo(prc2) >= 0) {
            return List.of(prc2, prc1);
        } else {
            return List.of(prc1, prc2);
        }
    }
}
