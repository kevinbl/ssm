package com.foo.ssm.mybatis.sharding;

/**
 *
 * version 1.0.0
 * Created by foolish on 16/9/9 下午3:05.
 */
public interface ISharding {
    /**
     * 一个dao 可能需要传递不同的参数，业务自己处理
     * @return table name
     */
    String  sharding(Object param);
}
