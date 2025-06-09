class Darts {

    private final int INNER_RADIUS = 1;
    private final int MIDDLE_RADIUS = 5;
    private final int OUTER_RADIUS = 10;

    int score(double xOfDart, double yOfDart) {
        double dartRadius = getDistanceFromCenter(xOfDart, yOfDart);
        int score = 0;
        if (dartRadius <= 1d) {
            score = 10;
        } else if (dartRadius <= 5d) {
            score = 5;
        } else if (dartRadius <= 10d) {
            score = 1;
        }
        return score;
    }

    private double getDistanceFromCenter(double x, double y) {
        return Math.sqrt(x * x + y * y);
    };
}
