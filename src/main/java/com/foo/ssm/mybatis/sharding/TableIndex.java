package com.foo.ssm.mybatis.sharding;

/**
 * version 1.0.0
 * Created by f on 16/10/20 下午4:37.
 */
public class TableIndex {
    public final Integer index;

    private TableIndex(Integer index){
        this.index=index;
    }

    public static final TableIndex of(Integer index){
        return new TableIndex(index);
    }
}
