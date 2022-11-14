package com.oracle.oracle_soda_database_helper;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>Builder</b> class for a {@code DataSource} using
 * a {@code HikariDataSource} as implementation.
 * @param <T> a derived {@code DataSourceBuilder} class
 */
public class DataSourceBuilder<T extends DataSourceBuilder> {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            DataSourceBuilder.class);
    
    private final HikariDataSource result;
    public DataSourceBuilder() {
        result = new HikariDataSource();
    }
    
    public final T driverClassName(final String driverClassName) {
        LOGGER.debug("driverClassName({})", driverClassName);
        result.setDriverClassName(driverClassName);
        return (T) this;
    }
    
    public final T jdbcUrl(final String url) {
        LOGGER.debug("jdbcUrl({})", url);
        result.setJdbcUrl(url);
        return (T) this;
    }
    
    public final T username(final String username) {
        LOGGER.debug("username({})", username);
        result.setUsername(username);
        return (T) this;
    }
    
    public final T password(final String password) {
        LOGGER.debug("password({})", password);
        result.setPassword(password);
        return (T) this;
    }
    
    public final T maximumPoolSize(final int maxPoolSize) {
        LOGGER.debug("maximumPoolSize({})", maxPoolSize);
        result.setMaximumPoolSize(maxPoolSize);
        return (T) this;
    }
    
    public final T autoCommit(final boolean autoCommit) {
        LOGGER.debug("autoCommit({})", autoCommit);
        result.setAutoCommit(autoCommit);
        return (T) this;
    }
    
    public final T dataSourceProperty(
            final String propertyName, final String propertyValue) {
        LOGGER.debug("dataSourceProperty({}, {})",
                propertyName, propertyValue);
        result.addDataSourceProperty(propertyName, propertyValue);
        return (T) this;
    }
    
    public final DataSource build() {
        return result;
    }
}
