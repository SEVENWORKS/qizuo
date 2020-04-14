package com.qizuo.base.plugin.mybatisPage;

import com.qizuo.base.model.page.PageDto;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: fangl
 * @Description: 分页sql执行器：重写的executor专为分页所写：包含分页语句拦截和count编写
 * @Date: 21:45 2018/11/19
 */
public class MybatisPageExecutor implements Executor {
    /**
     * 执行器
     */
    private Executor executor;
    /**
     * 分页匹配正则表达式
     */
    private String pattern;

    public MybatisPageExecutor(Executor executor, String pattern) {
        this.executor = executor;
        this.pattern = pattern;
    }

    /**
     * @Author: fangl
     * @Description: 主要复写的查询方法(还一种重写的查询方法, 不要复写那个, 最长的参数这个)
     * @Date: 15:50 2018/11/23
     */
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
                             CacheKey cacheKey, BoundSql boundSql) throws SQLException {
        //参数判断
        RowBounds rb = rowBounds;
        String msid = ms.getId();
        //判断是否符合分页条件(核心)
        if (msid.contains(pattern) && parameter instanceof PageDto) {
            //分页参数设定
            PageDto pageDto = (PageDto) parameter;
            rb = new RowBounds((pageDto.getPageNo() - 1) * pageDto.getPageSize(), pageDto.getPageSize());
            //count设定
            pageDto.setTotalCount(getCount(ms, parameter));
        }

        //分页开始
        List<E> rows = executor.query(ms, parameter, rb, resultHandler,
                cacheKey, boundSql);
        /* 这个地方是确认返回结果的地方，rows是执行成功获取的结果集。
         * 1.如果用下述pageResolver方法，有一个前提page类必须继承arraylist，但是注意这时候的page类是无法再springmvc上直接接受参数，
         * 因为springmvc不能直接接受List参数；但这样的好处是，直接就返回一个包装完整的page类，无须在进行封装数据到page。
         * 2.如果不适用下述pageResolver方法，可以直接返回rows，但是这样dao层上就要改成list<>，不能用page<>,并且page需要自己完整
         * 封装结果数据。
         * @author fanglu
         */
        return rows;
    }

    /** 以下都是获取count的方式 */
    /**
     * @Author: fangl
     * @Description: 获取count的总条数的首要方法
     * @Date: 16:09 2018/11/23
     */
    private int getCount(MappedStatement ms, Object parameter) {
        BoundSql bsql = ms.getBoundSql(parameter);
        String sql = bsql.getSql();
        //修改sql变成count语句
        String countSql = getCountSql(sql);
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //连接
            connection = ms.getConfiguration().getEnvironment().getDataSource()
                    .getConnection();
            //准备
            stmt = connection.prepareStatement(countSql);
            //参数设定
            setParameters(stmt, ms, bsql, parameter);
            //执行
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * @Author: fangl
     * @Description: count的sql生成(生成的时候去掉不必要的orderby;变成所有参数变成count)
     * @Date: 16:11 2018/11/23
     */
    private String getCountSql(String sql) {
        String countHql = " SELECT count(*) "
                + removeSelect(removeOrders(sql));

        return countHql;
    }

    //去除orderby方法
    protected String removeOrders(String sql) {
        Pattern pt = Pattern.compile("ORDER\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = pt.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    //select后面的所有参数变成count
    private static String removeSelect(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        if (beginPos < 0) {
            throw new IllegalArgumentException(" hql : " + hql + " must has a keyword 'from'");
        }
        return hql.substring(beginPos);
    }

    /**
     * @Author: fangl
     * @Description: count方法中设置参数
     * @Date: 16:10 2018/11/23
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        //这一句必须，没搞明白，应该是执行了参数传入
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        //获取所有参数
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            //获取参数对象的反射类
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    //参数的名字，属性
                    String propertyName = parameterMapping.getProperty();
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        //typeHandlerRegistry注册了某个类的处理
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else {
                        //默认的MetaObject 的处理，根据参数获取值,这个将和boundSql一起解释
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    @SuppressWarnings("rawtypes")
                    //参数列的TypeHandler
                            TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
                                + " of statement " + mappedStatement.getId());
                    }
                    //这里实现了 JDBC的 pst.setString(1,"Tom" + i);
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    /**************************************** 以下几种都是默认的方法 ************************************************/

    @Override
    public int update(MappedStatement ms, Object parameter) throws SQLException {
        return executor.update(ms, parameter);
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
            throws SQLException {
        BoundSql boundSql = ms.getBoundSql(parameter);
        return query(ms, parameter, rowBounds, resultHandler,
                executor.createCacheKey(ms, parameter, rowBounds, boundSql),
                boundSql);
    }

    @Override
    public <E> Cursor<E> queryCursor(MappedStatement mappedStatement, Object o, RowBounds rowBounds) throws SQLException {
        return null;
    }

    @Override
    public List<BatchResult> flushStatements() throws SQLException {
        return executor.flushStatements();
    }

    @Override
    public void commit(boolean required) throws SQLException {
        executor.commit(required);
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        executor.rollback(required);
    }

    @Override
    public CacheKey createCacheKey(MappedStatement ms, Object parameterObject,
                                   RowBounds rowBounds, BoundSql boundSql) {
        return executor
                .createCacheKey(ms, parameterObject, rowBounds, boundSql);
    }

    @Override
    public boolean isCached(MappedStatement ms, CacheKey key) {
        return executor.isCached(ms, key);
    }

    @Override
    public void clearLocalCache() {
        executor.clearLocalCache();
    }

    @Override
    public void deferLoad(MappedStatement ms, MetaObject resultObject,
                          String property, CacheKey key, Class<?> targetType) {
        executor.deferLoad(ms, resultObject, property, key, targetType);
    }

    @Override
    public Transaction getTransaction() {
        return executor.getTransaction();
    }

    @Override
    public void close(boolean forceRollback) {
        executor.close(forceRollback);
    }

    @Override
    public boolean isClosed() {
        return executor.isClosed();
    }

    @Override
    public void setExecutorWrapper(Executor executor) {

    }
}
