package com.example.metricslogger;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Raymond on 2016-06-19.
 */
@Aspect /** We define this class as an Aspect */
public class MetricsRuntimeAspect {

    /**
     * So we here to define our Pointcut, to let the AspectJ plugin know
     * where it should weave (inject) our advice (code).
     *
     * The expression (Jointpoint signature) 'execution(com.example.metricslogger.Metrics * *(..))'
     * shows that code should be weaved whenever any method '* *(..)' is annotated with the
     * '@com.example.metricslogger.Metrics' annotation
     *
     * Not necessary an annotation, actually you can define any methods here
     */
    @Pointcut("execution(@com.example.metricslogger.MetricsLog * *(..))")
    public void methodAnnotatedWithMetricsLog() {};

    /**
     * Similar rules apply for constructor methods
     */
    @Pointcut("execution(@com.example.metricslogger.MetricsLog *.new(..))")
    public void constructorAnnotatedWithMetricsLog() {};

    /**
     * The @Around annotation provides a feature like 'grouping' different pointcuts you defined above
     * Actually you can write the AspectJ syntax like
     *
     * execution(@com.example.metricslogger.MetricsLog * *(..)) || execution(@com.example.metricslogger.MetricsLog *.new(..))
     *
     * but this is clumsy and not nice
     */
    @Around("methodAnnotatedWithMetricsLog() || constructorAnnotatedWithMetricsLog()")
    public Object weaveJointpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MetricsLog metricsLogAnnotation = methodSignature.getMethod().getAnnotation(MetricsLog.class);

        Object result = joinPoint.proceed();
        String category = metricsLogAnnotation.category();
        String action = metricsLogAnnotation.action();

        Object[] obj = joinPoint.getArgs(); // Get function arguments

        long value = 0;
        for (Object anObj : obj) {
            if (anObj instanceof Long) {
                value = (Long) anObj; // Just a naive determination
                break;
            }
        }

        MetricsLogger.logEvent(category, action, value);

        return result;
    }
}
