import java.time.DayOfWeek;
import java.time.LocalDate;

public class Meetup {

    private final LocalDate date;

    public Meetup(int monthOfYear, int year) {
        date = LocalDate.of(year, monthOfYear, 1);
    }

    public LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
        int count = 0;
        int monthLen = date.getMonth().length(date.isLeapYear());
        switch (schedule) {
            case MeetupSchedule.LAST:
                for (int i = monthLen - 1; i >=0; i--) {
                    var candidate = date.plusDays(i);
                    if (candidate.getDayOfWeek() == dayOfWeek) {
                        return candidate;
                    }
                }
            case MeetupSchedule.TEENTH:
                for (int i = 12; i < 19; i++) {
                    var candidate = date.plusDays(i);
                    if (candidate.getDayOfWeek() == dayOfWeek) {
                        return candidate;
                    }  
                }
            default:
                count = schedule.ordinal() + 1;
                for (int i = 0; i < monthLen; i++) {
                    var candidate = date.plusDays(i);
                    if (candidate.getDayOfWeek() == dayOfWeek) {
                        count--;
                        if (count == 0) {
                            return candidate;
                        }
                    }  
                }
        }
        throw new RuntimeException("Can't find a proper meetup");
    }
}