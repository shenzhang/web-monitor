package io.github.shenzhang.monitor.performance;

import java.util.Date;

/**
 * User: shenzhang
 * Date: 1/25/15
 * Time: 2:58 PM
 */
public class PerformanceCaculateThread extends Thread {
    private PerformanceEventStore eventStore = PerformanceEventStore.getInstance();
    private PerformanceSummaryStore summaryStore = PerformanceSummaryStore.getInstance();

    public PerformanceCaculateThread() {
        setDaemon(true);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Date now = new Date();

        }
    }
}
