package io.github.shenzhang.monitor.performance;

import java.util.Date;

/**
 * User: shenzhang
 * Date: 1/25/15
 * Time: 2:41 PM
 */
public class PerformanceSummary {
    private String name;
    private Date timestamp;
    private int count;          // complete + waiting
    private int waitingCount;

    public PerformanceSummary(String name, Date timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(int waitingCount) {
        this.waitingCount = waitingCount;
    }
}
