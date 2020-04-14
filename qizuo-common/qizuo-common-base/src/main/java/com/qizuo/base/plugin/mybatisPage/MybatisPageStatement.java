package com.qizuo.base.plugin.mybatisPage;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author: fangl
 * @description: 复写一个StatementHandler，准确来说是复写RoutingStatementHandler对象，准备在拦截器中使用，暂时未用到。
 * @date: 9:58 2019/2/22
 */
public class MybatisPageStatement implements StatementHandler {

    private final StatementHandler delegate;

    public MybatisPageStatement(StatementHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public Statement prepare(Connection connection, Integer integer) throws SQLException {
        return this.delegate.prepare(connection,integer);
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        this.delegate.parameterize(statement);
    }

    @Override
    public void batch(Statement statement) throws SQLException {
        this.delegate.batch(statement);
    }

    @Override
    public int update(Statement statement) throws SQLException {
        return this.delegate.update(statement);
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        return this.delegate.query(statement, resultHandler);
    }

    @Override
    public <E> Cursor<E> queryCursor(Statement statement) throws SQLException {
        return null;
    }

    @Override
    public BoundSql getBoundSql() {
        return this.delegate.getBoundSql();
    }

    @Override
    public ParameterHandler getParameterHandler() {
        return this.delegate.getParameterHandler();
    }
}
