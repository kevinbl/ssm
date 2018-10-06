package com.foo.ssm.mybatis.sharding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标示某个方法轮询所有的表
 * version 1.0.0
 * Created by foolish on 16/9/13 下午2:14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoundAll {
}
