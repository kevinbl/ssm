package com.foo.ssm.mybatis.sharding.annotation;


import com.foo.ssm.mybatis.sharding.ISharding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 调用第一个参数的 shardBy 指定的字段 进行 分区
 * version 1.0.0
 * Created by foolish on 16/9/9 上午11:03.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sharding {
    String tableName();
    int tableCount();
    Class<? extends ISharding> using();
    Class[] paramTypes();
}
