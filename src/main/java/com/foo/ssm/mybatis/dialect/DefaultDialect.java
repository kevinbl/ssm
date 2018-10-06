package com.foo.ssm.mybatis.dialect;

/**
 * Dialect接口的MySQL实现，用于提供物理分页。
 * 
 * @author foolish
 */
public class DefaultDialect implements Dialect {

    // SQL结束标记.
    protected static final String SQL_END_DELIMITER = ";";

    public String getLimitString(String sql, int offset, int limit) {

        if (sql.endsWith(SQL_END_DELIMITER)) {
            CharSequence _sql = sql.subSequence(0, sql.length() - SQL_END_DELIMITER.length());
            return append(new StringBuilder(_sql), offset, limit).append(SQL_END_DELIMITER).toString();
        }

        return append(new StringBuilder(sql), offset, limit).toString();
    }

    private StringBuilder append(StringBuilder builder, int offset, int limit) {
        builder.append(" limit ").append(limit);

        if (offset > 0) {
            builder.append(" offset ").append(offset);
        }

        return builder;
    }
}
