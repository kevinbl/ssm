package com.foo.ssm.mybatis.sharding.interceptor;

import com.foo.ssm.mybatis.sharding.TableIndex;
import com.foo.ssm.mybatis.sharding.annotation.Sharding;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * version 1.0.0
 * Created by f on 16/9/9 上午10:58.
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class})})
public class ShardingTableInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(ShardingTableInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation
                .getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(
                statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);

        String originalSql = null;
        BoundSql boundSql = null;
        try {
            originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
            boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        } catch (Exception e) {

        }
        if (originalSql == null) {
            originalSql = (String) metaStatementHandler.getValue("h.target.delegate.boundSql.sql");
        }
        if (boundSql == null) {
            boundSql = (BoundSql) metaStatementHandler.getValue("h.target.delegate.boundSql");
        }
        //Configuration configuration = (Configuration) metaStatementHandler
        //.getValue("delegate.configuration");
        if (originalSql != null && !originalSql.equals("")) {
            MappedStatement mappedStatement = null;
            try {
                mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            } catch (Exception e) {

            }
            if (mappedStatement == null) {
                mappedStatement = (MappedStatement) metaStatementHandler.getValue("h.target.delegate.mappedStatement");
            }
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            Class<?> classObj = Class.forName(className);
            //根据配置自动生成分表SQL
            Sharding sharding = classObj.getAnnotation(Sharding.class);

            if (sharding != null) {

                //todo 通过id 上的注解判断是否查所有表
                MapperMethod.ParamMap params = (MapperMethod.ParamMap) boundSql.getParameterObject();
                String newTableName = sharding.using().newInstance().sharding(findShardingParam(params, sharding));
                String newSql = originalSql.replace(sharding.tableName(), newTableName);
                try {

                    metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
                } catch (Throwable e) {
                    metaStatementHandler.setValue("h.target.delegate.boundSql.sql", newSql);
                }
                logger.debug("table change :{} :{}",sharding.tableName(),newTableName);
            }
        }
        // 传递给下一个拦截器处理
        return invocation.proceed();

    }

    private Object findShardingParam(MapperMethod.ParamMap params, Sharding sharding) {
        for (Object o : params.values()) {
            if (o != null && o.getClass() == TableIndex.class) {
                return o;
            }
        }

        for (Object o : params.values()) {
            for (Class aClass : sharding.paramTypes()) {
                if (o != null && o.getClass() == aClass) {
                    return o;
                }
            }
        }
        throw new IllegalArgumentException("该方法的参数没有在指定的分页类型列表中：paramTypes");
    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }

    }

    @Override
    public void setProperties(Properties properties) {

    }


}
