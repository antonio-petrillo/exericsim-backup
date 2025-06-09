public class LogLine {

    private final LogLevel level;
    private final String message;

    public LogLine(String logLine) {
        message = logLine;
        switch (logLine.substring(1,4)){
            case "TRC":
                level = LogLevel.TRACE;
                break;
            case "DBG":
                level = LogLevel.DEBUG;
                break;
            case "INF":
                level = LogLevel.INFO;
                break;
            case "WRN":
                level = LogLevel.WARNING;
                break;
            case "ERR":
                level = LogLevel.ERROR;
                break;
            case "FTL":
                level = LogLevel.FATAL;
                break;
            default:
                level = LogLevel.UNKNOWN;
        }
    }

    public LogLevel getLogLevel() {
        return level;
    }

    public String getOutputForShortLog() {
        return String.format("%d:%s", level.getId(), message.substring(7));
    }
}
