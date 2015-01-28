package io.github.shenzhang.monitor;

import io.github.shenzhang.monitor.handler.AspectHandler;
import io.github.shenzhang.monitor.handler.FinalAspectHandler;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * User: shenzhang
 * Date: 1/20/15
 * Time: 3:48 PM
 */
public class DefaultMonitorAspect {
    private AspectHandler handler = new FinalAspectHandler();

    public Object run(final ProceedingJoinPoint joinPoint) {
        return handler.run(joinPoint);
    }
}
