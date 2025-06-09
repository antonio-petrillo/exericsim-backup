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
        while(lastDay.getDayOfWeek() != dayOfWeek) {
            lastDay = lastDay.plusDays(-1);
        }
        return lastDay;
    }

    private LocalDate getFirstDay(DayOfWeek dayOfWeek) {
        LocalDate firstDay = date;
        while(firstDay.getDayOfWeek() != dayOfWeek) {
            firstDay = firstDay.plusDays(1);
        }
        return firstDay;
    }

    private LocalDate getTeenthDay(DayOfWeek dayOfWeek) {
        LocalDate teenthDay = date.plusDays(12); // first day + twelve days
        while(teenthDay.getDayOfWeek() != dayOfWeek) {
            teenthDay = teenthDay.plusDays(1);
        }
        return teenthDay;
    }
    
    public LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
        int count = 0;
        int monthLen = date.getMonth().length(date.isLeapYear());
        LocalDate result = switch (schedule) {
            case MeetupSchedule.LAST -> getLastDay(dayOfWeek);
            case MeetupSchedule.TEENTH -> getTeenthDay(dayOfWeek);
            case MeetupSchedule.FIRST -> getFirstDay(dayOfWeek);
            case MeetupSchedule.SECOND -> getFirstDay(dayOfWeek).plusDays(7);
            case MeetupSchedule.THIRD -> getFirstDay(dayOfWeek).plusDays(14);
            case MeetupSchedule.FOURTH -> getFirstDay(dayOfWeek).plusDays(21);
        };
        return result;
    }
}