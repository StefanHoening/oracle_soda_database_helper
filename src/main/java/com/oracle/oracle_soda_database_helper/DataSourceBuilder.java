package com.oracle.oracle_soda_database_helper;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * <b>Builder</b> class for a {@code DataSource} using
 * a {@code HikariDataSource} as implementation.
 * @param <T> a derived {@code DataSourceBuilder} class
 */
public class DataSourceBuilder<T extends DataSourceBuilder> {
    private final HikariDataSource result;
    public DataSourceBuilder() {
        result = new HikariDataSource();
    }
    
    public final T driverClassName(final String driverClassName) {
        result.setDriverClassName(driverClassName);
        return (T) this;
    }
    
    public final T jdbcUrl(final String url) {
        result.setJdbcUrl(url);
        return (T) this;
    }
    
    public final T username(final String username) {
        result.setUsername(username);
        return (T) this;
    }
    
    public final T password(final String password) {
        result.setPassword(password);
        return (T) this;
    }
    
    public final T maximumPoolSize(final int maxPoolSize) {
        result.setMaximumPoolSize(maxPoolSize);
        return (T) this;
    }
    
    public final T dataSourceProperty(
            final String propertyName, final String propertyValue) {
        result.addDataSourceProperty(propertyName, propertyValue);
        return (T) this;
    }
    
    public final DataSource build() {
        return result;
    }
}
