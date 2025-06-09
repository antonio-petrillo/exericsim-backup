import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Map;
import java.util.EnumMap;

public class Meetup {

    private final LocalDate date;
    private static final Map<MeetupSchedule, Function<DayOfWeek, Predicate<LocalDate>>> clauses = new EnumMap<MeetupSchedule, Function<DayOfWeek, Predicate<LocalDate>>>(MeetupSchedule.class);

    static {
        clauses.put(
            MeetupSchedule.FIRST,
            dayOfWeek -> {
                return day -> day.getDayOfWeek() == dayOfWeek;
            }
        );

        clauses.put(
            MeetupSchedule.SECOND,
            dayOfWeek -> {
                return new Predicate<LocalDate>() {
                    int count = 0;
                    
                    public boolean test(LocalDate date) {
                        if (date.getDayOfWeek() == dayOfWeek) {
                            count++;
                            return count == 2;
                        }
                        return false;
                    }
                };
            }
        );  
        
        clauses.put(
            MeetupSchedule.THIRD,
            dayOfWeek -> {
                return new Predicate<LocalDate>() {
                    int count = 0;
                    
                    public boolean test(LocalDate date) {
                        if (date.getDayOfWeek() == dayOfWeek) {
                            count++;
                            return count == 3;
                        }
                        return false;
                    }
                };
            }
        );  
                
        clauses.put(
            MeetupSchedule.FOURTH,
            dayOfWeek -> {
                return new Predicate<LocalDate>() {
                    int count = 0;
                    
                    public boolean test(LocalDate date) {
                        if (date.getDayOfWeek() == dayOfWeek) {
                            count++;
                            return count == 4;
                        }
                        return false;
                    }
                };
            }
        );  

        clauses.put(
            MeetupSchedule.LAST,
            dayOfWeek -> {
                return day -> day.getDayOfWeek() == dayOfWeek && day.plusDays(7).getMonth() != day.getMonth();
            }
        );

        clauses.put(
            MeetupSchedule.TEENTH,
            dayOfWeek -> {
                return day -> day.getDayOfWeek() == dayOfWeek && day.getDayOfMonth() >= 13 && day.getDayOfMonth() <= 19;
            }
        );
    }

    public Meetup(int monthOfYear, int year) {
        date = LocalDate.of(year, monthOfYear, 1);
    }

    public LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
        Predicate<LocalDate> pred = clauses.get(schedule).apply(dayOfWeek);
        
        for (int i = 0; i < date.getMonth().length(date.isLeapYear()); i++) {
            LocalDate candidate = date.plusDays(i);
            if (pred.test(candidate)) {
                return candidate;
            }
        }
        throw new RuntimeException("Can't find a proper meetup");
    }

}