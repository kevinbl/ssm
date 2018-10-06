package com.foo.ssm.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 简单全局异常处理 Created by foolish on 16-11-7.
 */
public class SimpleHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ModelAndView web500 = new ModelAndView("500").addObject("errmsg", "服务器异常");

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        LOGGER.error("服务器异常--：{}", ex.getMessage(), ex);
        return web500;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
