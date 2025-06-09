// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

public class LogLevels {
    
    public static String message(String logLine) {
        // throw new UnsupportedOperationException("Please implement the (static) LogLine.message() method");
        int index = logLine.indexOf(" ");
        return logLine.substring(index + 1).trim();
    }

    public static String logLevel(String logLine) {
        // throw new UnsupportedOperationException("Please implement the (static) LogLine.logLevel() method");
        // Pattern pattern = Pattern.compile("[A-Z]*", Pattern.CASE_INSENSITIVE);
        // Matcher matcher = pattern.matcher(logLine);
        // matcher.matches();
        // return matcher.group(0).toLowerCase();

        int indexCloseSquareBracket = logLine.indexOf("]:");

        return logLine.substring(1, indexCloseSquareBracket).toLowerCase();
    }


    public static String reformat(String logLine) {
        // throw new UnsupportedOperationException("Please implement the (static) LogLine.reformat() method");
        var message = LogLevels.message(logLine);
        var level = LogLevels.logLevel(logLine);
        return message + " (" + level + ")";
    }
}
