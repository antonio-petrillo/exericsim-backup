import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {

    private LocalDateTime date;

    public Gigasecond(LocalDate moment) {
        this(moment.atTime(0, 0));
    }

    public Gigasecond(LocalDateTime moment) {
        date = moment.plusSeconds(1000000000);
    }

    public LocalDateTime getDateTime() {
        return date;
    }
}
