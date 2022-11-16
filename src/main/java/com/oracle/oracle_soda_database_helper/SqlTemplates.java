package com.oracle.oracle_soda_database_helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 *
 * @author stefan
 */
public final class SqlTemplates {
    
    private SqlTemplates() {}
    
    
    public static <R> R apply(
            final DataSource dataSource,
            final SqlFunction<Connection, R> function,
            final boolean commit) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            R result = function.apply(connection);
            if (commit) {
                connection.commit();
            }
            return result;
        }
    }
    
    public static <R> R apply(
            final Connection connection,
            final String sqlStatement,
            final SqlFunction<PreparedStatement, R> function) throws SQLException {
        try (PreparedStatement statement
                = connection.prepareStatement(sqlStatement)) {
            return function.apply(statement);
        }
    }
    
    public static <R> R apply(
            final PreparedStatement preparedStatement,
            final SqlFunction<ResultSet, R> function) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            return function.apply(resultSet);
        }
    }

    
    public static void accept(
            final DataSource dataSource,
            final SqlConsumer<Connection> consumer,
            final boolean commit) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            consumer.accept(connection);
            if (commit) {
                connection.commit();
            }
        }
    }
    
    public static void accept(
            final Connection connection,
            final String sqlStatement,
            final SqlConsumer<PreparedStatement> consumer) throws SQLException {
        try (PreparedStatement statement
                = connection.prepareStatement(sqlStatement)) {
            consumer.accept(statement);
        }
    }
    
}
