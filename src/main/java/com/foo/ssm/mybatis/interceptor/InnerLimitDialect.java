package com.foo.ssm.mybatis.interceptor;

import org.apache.ibatis.session.RowBounds;

import com.foo.ssm.mybatis.dialect.DefaultDialect;

/**
 * 该Dialect实现类中附加了对SQL中的@LIMIT占位符的处理逻辑
 *
 * Version 1.1.0 Created by foolish on 16/11/26.
 */
public class InnerLimitDialect extends DefaultDialect {

    // Limit占位符
    protected static final String PLACEHOLDER_OF_LIMIT = "@LIMIT";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        if (sql.contains(PLACEHOLDER_OF_LIMIT)) {
            String limitStr = isValidLimit(limit) ? " limit " + limit + " offset " + offset : "";
            sql = sql.replace(PLACEHOLDER_OF_LIMIT, limitStr);
        } else if (isValidLimit(limit)) {
            sql = super.getLimitString(sql, offset, limit);
        }
        return sql;
    }

    private boolean isValidLimit(int limit) {
        return limit > 0 && limit < RowBounds.NO_ROW_LIMIT;
    }
}
