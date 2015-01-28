package io.github.shenzhang.monitor.handler;

import io.github.shenzhang.monitor.expection.MonitorException;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * User: shenzhang
 * Date: 1/23/15
 * Time: 2:11 PM
 */
public class FinalAspectHandler extends AspectHandler {
    @Override
    public Object run(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable cause) {
            throw new MonitorException(cause);
        }
    }
}
