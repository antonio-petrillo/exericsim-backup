import java.time.DayOfWeek;
import java.time.LocalDate;

public class Meetup {

    private final LocalDate date;

    public Meetup(int monthOfYear, int year) {
        date = LocalDate.of(year, monthOfYear, 1);
    }

    private LocalDate getLastDay(DayOfWeek dayOfWeek) {
        // thanks to:
        // https://exercism.org/tracks/java/exercises/meetup/solutions/artemkorsakov
        LocalDate lastDay = date.plusMonths(1).plusDays(-1);
        for(; lastDay.getDayOfWeek() != dayOfWeek; lastDay = lastDay.plusDays(-1)) {}
        return lastDay;
    }

    private LocalDate getFirstDay(DayOfWeek dayOfWeek) {
        LocalDate firstDay = date;
        for(; firstDay.getDayOfWeek() != dayOfWeek; firstDay = firstDay.plusDays(1)) {}
        return firstDay;
    }

    private LocalDate getTeenthDay(DayOfWeek dayOfWeek) {
        LocalDate teenthDay = date.plusDays(12); // first day + twelve days
        for(; teenthDay.getDayOfWeek() != dayOfWeek; teenthDay = teenthDay.plusDays(1)) {}
        return teenthDay;
    }
    
    public LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
        int count = 0;
        int monthLen = date.getMonth().length(date.isLeapYear());
        switch (schedule) {
            case MeetupSchedule.LAST:
                return getLastDay(dayOfWeek);
            case MeetupSchedule.TEENTH:
                return getTeenthDay(dayOfWeek);
            case MeetupSchedule.FIRST:
                return getFirstDay(dayOfWeek);
            case MeetupSchedule.SECOND:
                return getFirstDay(dayOfWeek).plusDays(7);
            case MeetupSchedule.THIRD:
                return getFirstDay(dayOfWeek).plusDays(14);
            case MeetupSchedule.FOURTH:
                return getFirstDay(dayOfWeek).plusDays(21);
        }
        throw new RuntimeException("This should be unreachable");
    }
}