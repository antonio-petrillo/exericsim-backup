import java.time.Duration;
import java.time.Instant;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter<K> {
    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    private ConcurrentMap<K, Window> clients = new ConcurrentHashMap<>();

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
    }

    public boolean allow(K clientId) {
        var now = timeSource.now();
        var window = clients.computeIfAbsent(clientId, _ -> new Window(now));

        synchronized (window) {
            if (!now.isBefore(window.start.plus(windowSize))) {
                window.start = now;
                window.count = 0;
            }

            if (window.count == limit)
                return false;

            window.count++;
        }

        return true;
    }

    private class Window {
        Instant start;
        int count = 0;

        Window(Instant start) {
            this.start = start;
        }
    }
}
