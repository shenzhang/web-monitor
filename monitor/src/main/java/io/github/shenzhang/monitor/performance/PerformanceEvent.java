package io.github.shenzhang.monitor.performance;

import java.util.Date;

/**
 * User: shenzhang
 * Date: 1/25/15
 * Time: 2:33 PM
 */
public class PerformanceEvent implements Comparable<PerformanceEvent> {
    private EventStatus status = EventStatus.WAITING;
    private String service;
    private long duration;
    private Date beginTimestamp;
    private Date timestamp;

    public PerformanceEvent(String service, Date tiemstamp) {
        this.service = service;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(PerformanceEvent o) {
        return timestamp.compareTo(o.timestamp);
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Date getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(Date beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
