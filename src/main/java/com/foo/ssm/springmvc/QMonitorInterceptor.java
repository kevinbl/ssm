package com.foo.ssm.springmvc;

import com.google.common.base.Stopwatch;
import com.qunar.flight.qmonitor.QMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaozheng.zhao on 2017/3/13.
 * Controller接口监控,基于spring拦截器实现
 * <mvc:interceptor>
       <mvc:mapping path="/**"/>
       <bean class="com.qunar.flight.jy.common.web.QMonitorInterceptor"/>
   </mvc:interceptor>
 */
public class QMonitorInterceptor extends HandlerInterceptorAdapter {
    private static final String CONSUME_STOPWATCH = "consumeStopwatch";
    private static final String REQUEST_PATH = "requestPath";
    private static final Logger logger = LoggerFactory.getLogger(QMonitorInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute(CONSUME_STOPWATCH, Stopwatch.createStarted());
        request.setAttribute(REQUEST_PATH, request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long time = ((Stopwatch) request.getAttribute(CONSUME_STOPWATCH)).elapsed(TimeUnit.MILLISECONDS);
        String requestPath = (String) request.getAttribute(REQUEST_PATH);
        logger.info("MonitorPath={},time={}ms", requestPath,time);
        QMonitor.recordOne(requestPath.replace("/", "_"), time);
    }

}
