package io.github.shenzhang.monitor.performance;

/**
 * User: shenzhang
 * Date: 1/25/15
 * Time: 2:40 PM
 */
public class PerformanceSummaryStore {
    private static final PerformanceSummaryStore instance = new PerformanceSummaryStore();

    private PerformanceSummaryStore() {
    }

    public static PerformanceSummaryStore getInstance() {
        return instance;
    }


}
