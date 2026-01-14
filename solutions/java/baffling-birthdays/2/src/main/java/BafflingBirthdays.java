import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BafflingBirthdays {
    private static final long MIN_DAY = LocalDate.of(1970, 1, 1).toEpochDay();
    private static final long MAX_DAY = LocalDate.of(9999, 12, 31).toEpochDay();

    public boolean sharedBirthday(List<LocalDate> birthdates) {
        return birthdates.stream()
            .map(date -> date.withYear(0))
            .distinct()
            .count() != birthdates.size();
    }

    public List<LocalDate> randomBirthdates(int groupSize) {
        final var rng = new Random();

        return Stream.generate(() -> {
                long day = rng.nextLong(MIN_DAY, MAX_DAY);
                return LocalDate.ofEpochDay(day);
            })
            .filter(date -> !date.isLeapYear())
            .limit(groupSize)
            .toList();
    }

    public double estimatedProbabilityOfSharedBirthday(int groupSize) {
        if (groupSize > 365) return 1.0;
        double different = 1.0;
        for (var i = 0; i < groupSize; i++) {
            different *= (365 - i);
        }
        different /= Math.pow(365.0, groupSize);
        return (1.0 - different) * 100.0;
    }
}
