package com.qizuo.base.interceptor.mybatisPage;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @Author: fangl
 * @Description: mybatis拦截器——分页(配合重写的执行器和分页sql生成工厂使用)
 * @Date: 17:09 2018/10/30
 */
@Intercepts({
        /**注意这个singnature,是很关键的，它决定返回代理对象后进不进入intercept，只有这个地方设定对应type后才会进行拦截
         * method是拦截类type中的方法，args是这个method中参数
         * 注意这三个对象都可以在Invocation这个类中获取到
         */
        @Signature(
                method = "query",
                type = Executor.class,
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                method = "prepare",
                type = StatementHandler.class,
                args = {Connection.class}
        )
})
public class MybatisPage implements Interceptor {
    /**
     * 0默认mybatis对象实例化工厂(可以用来替换别的)(作用是来生产mybatis对象和拦截器对象)
     */
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_OBJECT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    /**
     * 0需要进行分页操作的字符串正则表达式
     */
    private String pattern = "PageQZ";
    /**
     * 0数据库方言：目前只支持mysql、oracle、sqlServer；默认mysql
     */
    private String dialect = "mysql";

    /**
     * 1@Author: fangl
     *
     * @Description: a.首先进入这个方法，在这个方法中如果直接返回o，则不会进入intercept中，如果返回的是代理对象(plugin.warp)，则会进入intercept中
     * b.如果返回的代理对象是某些重写对象，则还在进入到intercept后，重新执行该重写对象中方法
     * c.总共会拦截四个对象resultset/StatementHandler等，这四个也是有顺序的(Executor/ParameterHandler/StatementHandler/ResultHandler)
     * d.这个方法只做决定是否返回代理对象和返回怎么样的代理的代理对象，其它修改参数或者获取参数等操作都不在其中，都到intercept进行操作
     * e.注意只有Signature标签中拦截了这个四个类其中一个，才会在plugins方法执行后，进入到intercept中
     * @Date: 11:41 2018/11/23
     */
    @Override
    public Object plugin(Object o) {
        //isAssignableFrom和instanceof区别：instanceof只会比较当前类，前者还会比较父类
        if (Executor.class.isAssignableFrom(o.getClass())) {
            //拦截执行器
            //获取分页执行器和拦截器对象(这个主要是设置count和设置RowBounds分页数值)
            MybatisPageExecutor executor = new MybatisPageExecutor((Executor) o, pattern);
            //返回执行器代理对象
            return executor;
        } else if (o instanceof StatementHandler) {
            //拦截语句(语句做limit分页添加改变)
            //返回语句代理对象
            return Plugin.wrap(o, this);
        }

        return o;
    }

    /**
     * 2@Author: fangl
     *
     * @Description: 如果plugin返回的是代理对象，则会在执行到plugin方法后立刻转到这个方法中，并且你可以在这个方法中针对那一步的代理对象做相应的操作
     * @Date: 11:43 2018/11/23
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            //如果是分页就会进行参数处理(这个方法内部会判断rowBounds对象，只有符合的rowBounds对象才会进行相关分页处理)
            return handleStatementHandler(invocation);
        }
        return invocation.proceed();
    }

    /**
     * 3@Author: fangl
     *
     * @Description: 拦截StatementHandler后要改装分页sql的地方
     * @Date: 22:54 2018/11/19
     */
    private Object handleStatementHandler(Invocation invocation)
            throws InvocationTargetException, IllegalAccessException {
        //执行语句拦截器
        StatementHandler statementHandler = (StatementHandler) invocation
                .getTarget();

        /**执行语句对象(MetaObject是Mybatis提供的一个的工具类，通过它包装一个对象后可以获取或设置该对象的原本不可访问的属性)
         1)       MetaObject forObject(Object object,ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory)

         2)       Object getValue(String name)

         3)       void setValue(String name, Object value)

         方法1)用于包装对象；方法2)用于获取属性的值（支持OGNL的方法）；方法3)用于设置属性的值（支持OGNL的方法）；*/
        MetaObject metaStatementHandler = MetaObject.forObject(
                statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_OBJECT_REFLECTOR_FACTORY);
        RowBounds rowBounds = (RowBounds) metaStatementHandler
                .getValue("delegate.rowBounds");

        //为null处理
        if (rowBounds == null || (rowBounds.getOffset() == RowBounds.NO_ROW_OFFSET && rowBounds
                .getLimit() == RowBounds.NO_ROW_LIMIT)) {
            return invocation.proceed();
        }

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object,
                    DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_OBJECT_REFLECTOR_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object,
                    DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_OBJECT_REFLECTOR_FACTORY);
        }

        // 将mybatis的内存分页，调整为物理分页
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        String sql = boundSql.getSql();
        // 重写sql
        String pageSql = MybatisPageSqlFactory.getPageSqlByDialect(this.dialect, sql, rowBounds);
        metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
        // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    /**
     * @Author: fangl
     * @Description: mybatis初始化配置参数方法
     * @Date: 11:41 2018/11/23
     */
    @Override
    public void setProperties(Properties properties) {
    }
}
