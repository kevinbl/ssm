package com.foo.ssm.mybatis.interceptor;

import com.foo.ssm.mybatis.dialect.Dialect;
import com.foo.ssm.reflect.ReflectUtil;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Properties;

/**
 * 改造过的物理分页拦截器, 支持SQL内的LIMIT操作, 对SQL内的@LIMIT占位符进行limit替换
 *
 * Version 1.1.0
 * Created by foolish on 16/11/26.
 * Updated by foolish on 16/11/27.
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }) })
public class InnerLimitStatementHandlerInterceptor extends StatementHandlerInterceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 获取处理目标
        StatementHandler target = (StatementHandler) invocation.getTarget();
        if (target instanceof RoutingStatementHandler) {
            target = (BaseStatementHandler) ReflectUtil.getFieldValue(target, "delegate");
        }
        RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(target, "rowBounds");

        // 调整查询字符串
        if (rowBounds.getLimit() >= 0 && rowBounds.getLimit() <= RowBounds.NO_ROW_LIMIT) {  // 此处对limit=0和limit=Integer.MAX_VALUE的情况也作了处理, 使得@Limit占位符总能被替换掉
            BoundSql boundSql = target.getBoundSql();
            String sql = boundSql.getSql();

            Dialect dialect = (Dialect) ReflectUtil.getFieldValue(this, "dialect");
            sql = dialect.getLimitString(sql, rowBounds.getOffset(), rowBounds.getLimit());
            ReflectUtil.setFieldValue(boundSql, "sql", sql);
        }

        // 执行查询处理
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 以自定义的dialectClass覆盖默认
        properties.put("dialectClass", InnerLimitDialect.class.getName());
        super.setProperties(properties);
    }
}
