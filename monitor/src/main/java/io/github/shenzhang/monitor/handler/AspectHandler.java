package io.github.shenzhang.monitor.handler;

import io.github.shenzhang.monitor.expection.MonitorException;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * User: shenzhang
 * Date: 1/20/15
 * Time: 4:02 PM
 */
public abstract class AspectHandler {
    protected AspectHandler next;

    public Object run(final ProceedingJoinPoint joinPoint) throws MonitorException {
        return next.run(joinPoint);
    }

    public void setNext(AspectHandler next) {
        this.next = next;
    }
}
