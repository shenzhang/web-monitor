package io.github.shenzhang.monitor.handler;

import io.github.shenzhang.monitor.expection.MonitorException;
import io.github.shenzhang.monitor.performance.EventStatus;
import io.github.shenzhang.monitor.performance.PerformanceEvent;
import io.github.shenzhang.monitor.performance.PerformanceEventStore;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Date;

/**
 * User: shenzhang
 * Date: 1/23/15
 * Time: 2:16 PM
 */
public class PerformanceAspectHandler extends AspectHandler {
    private PerformanceEventStore eventStore = PerformanceEventStore.getInstance();
    private String serviceName;

    @Override
    public Object run(ProceedingJoinPoint joinPoint) throws MonitorException {
        PerformanceEvent event = new PerformanceEvent(serviceName, new Date());
        eventStore.addEvent(event);
        try {
            Object value = super.run(joinPoint);
            success(event);
            return value;
        } catch (MonitorException e) {
            failed(event);
            throw e;
        }
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    private void success(PerformanceEvent event) {
        event.setStatus(EventStatus.SUCCESS);
        event.setBeginTimestamp(event.getTimestamp());
        event.setTimestamp(new Date());

        eventStore.refreshEvent(event);
    }

    private void failed(PerformanceEvent event) {
        event.setStatus(EventStatus.FAILED);
        event.setBeginTimestamp(event.getTimestamp());
        event.setTimestamp(new Date());

        eventStore.refreshEvent(event);
    }
}
