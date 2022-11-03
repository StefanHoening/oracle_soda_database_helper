package com.oracle.oracle_soda_database_helper;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 *
 */
public final class DataSourceBuilder {
    private final HikariDataSource result;
    public DataSourceBuilder() {
        result = new HikariDataSource();
    }
    
    public DataSourceBuilder jdbcUrl(final String url) {
        result.setJdbcUrl(url);
        return this;
    }
    
    public DataSourceBuilder username(final String username) {
        result.setUsername(username);
        return this;
    }
    
    public DataSourceBuilder password(final String password) {
        result.setPassword(password);
        return this;
    }
    
    public DataSourceBuilder maximumPoolSize(final int maxPoolSize) {
        result.setMaximumPoolSize(maxPoolSize);
        return this;
    }
    
    public DataSourceBuilder dataSourceProperty(
            final String propertyName, final String propertyValue) {
        result.addDataSourceProperty(propertyName, propertyValue);
        return this;
    }
    
    public DataSource build() {
        return result;
    }
}
