package aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * User: shenzhang
 * Date: 1/25/15
 * Time: 12:50 PM
 */
public class AopAspect {
    public Object process(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("before");
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("end");
        }
    }
}
