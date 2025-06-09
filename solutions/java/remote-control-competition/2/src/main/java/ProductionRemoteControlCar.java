public class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {

    private int distance = 0;
    private int numVictories = 0;
    
    public void drive() {
        distance += 10;
    }

    public int getDistanceTravelled() {
        return distance;
    }

    public int getNumberOfVictories() {
        return numVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numVictories = numberOfVictories;
    }

    public int compareTo(ProductionRemoteControlCar car) {
        return Integer.compare(car.numVictories, numVictories);
    }
}
