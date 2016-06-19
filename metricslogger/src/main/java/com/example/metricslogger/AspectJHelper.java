package com.example.metricslogger;

import org.aspectj.lang.JoinPoint;

/**
 * Created by Raymond on 2016-06-20.
 */

public class AspectJHelper {
    public static final String getJoinPointName(final JoinPoint joinPoint) {
        return joinPoint.getThis().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
    }

    public static final String getJointPointArgs(final JoinPoint joinPoint) {
        final StringBuilder buf = new StringBuilder();
        for (final Object arg : joinPoint.getArgs()) {
            buf.append(arg.getClass().getSimpleName() + "-" + arg + "+");
        }
        return buf.toString().replaceAll("\\+$", "");
    }
}
