import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLevels {
    
    public static String message(String logLine) {
        // throw new UnsupportedOperationException("Please implement the (static) LogLine.message() method");
        int index = logline.indexOf(" ");
        return logline.substring(index + 1).trim();
    }

    public static String logLevel(String logLine) {
        // throw new UnsupportedOperationException("Please implement the (static) LogLine.logLevel() method");
        Pattern pattern = Pattern.compile("\[[A-Z]*\]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(logline);
        return matcher.group(1);
    }

    public static String reformat(String logLine) {
        // throw new UnsupportedOperationException("Please implement the (static) LogLine.reformat() method");
        var message = LogLevels.message(logline);
        var level = LogLevels.logLevel(logline);
        return message + " (" + levele + ")";
    }
}
