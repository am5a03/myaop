package com.example.metricslogger;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Raymond on 2016-06-19.
 */
@Aspect
public class LogLifeCycleAspect {

    /**
     * To log all onCreate, onResume... lifecycle events
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* on*(..))") //Just a naive selection of method name
    public Object logLifecycle(ProceedingJoinPoint joinPoint) throws Throwable {
        String lifecycleName = ((MethodSignature)joinPoint.getSignature()).getMethod().getName();
        Log.d("LogLifeCycle", lifecycleName + ": ");
        return joinPoint.proceed();
    }
}
