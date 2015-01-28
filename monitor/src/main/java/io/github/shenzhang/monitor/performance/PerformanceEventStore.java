package io.github.shenzhang.monitor.performance;

/**
 * User: shenzhang
 * Date: 1/25/15
 * Time: 2:40 PM
 */
public class PerformanceEventStore {
    private static final PerformanceEventStore instance = new PerformanceEventStore();

    private PerformanceEventStore() {
    }

    public static PerformanceEventStore getInstance() {
        return instance;
    }

    public void addEvent(PerformanceEvent event) {

    }

    public void removeEvent(PerformanceEvent event) {

    }

    public void refreshEvent(PerformanceEvent event) {
        removeEvent(event);
        addEvent(event);
    }
}
