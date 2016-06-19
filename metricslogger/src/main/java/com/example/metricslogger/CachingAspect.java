package com.example.metricslogger;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;

/**
 * Created by Raymond on 2016-06-19.
 */
@Aspect
public class CachingAspect {

    private static final String TAG = "CachingAspect";
    private HashMap<String, Object> simpleCache;

    CachingAspect() {
        simpleCache = new HashMap<>();
    }

    @Pointcut("execution(@com.example.metricslogger.Cacheable * *(..))")
    private void cache(){};

    @Around("cache()")
    public Object aroundCacheable(ProceedingJoinPoint joinPoint) throws Throwable {
        final String jointPointName = AspectJHelper.getJoinPointName(joinPoint);
        final String jointPointArgs = AspectJHelper.getJointPointArgs(joinPoint);

        final String cacheKey = jointPointName + "-" + jointPointArgs;


        if (!simpleCache.containsKey(cacheKey)) {
            Object obj = joinPoint.proceed();
            simpleCache.put(cacheKey, obj);
            Log.d(TAG, "aroundCacheable: CACHE MISS! Key=" + cacheKey);
        } else {
            Log.d(TAG, "aroundCacheable: CACHE HIT! Key=" + cacheKey);
        }
        return simpleCache.get(cacheKey);
    }
}
