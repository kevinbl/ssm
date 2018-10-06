package com.foo.ssm.log;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 注解切面
 *
 * Created by foolish on 2017/2/6.
 */
@Aspect
@Component
public class LogAop {
    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);


    @Pointcut("@annotation(com.qunar.flight.jy.common.log.Log)")
    public void logDao() {
    }

    // 打日志记录运行时间
    @Around(value = "logDao()")
    public Object around(JoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return ((ProceedingJoinPoint) joinPoint).proceed();
        }finally {
            long runTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            logger.info("{}方法运行了{}ms", joinPoint.getSignature().getName(), runTime);
        }
    }
}
