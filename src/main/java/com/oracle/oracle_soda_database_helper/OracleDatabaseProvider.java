package com.oracle.oracle_soda_database_helper;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.soda.OracleException;

/**
 *
 */
public class OracleDatabaseProvider {
    
    private final DataSource dataSource;
    
    public OracleDatabaseProvider(final DataSource theDataSource) {
        dataSource = theDataSource;
    }
    
    public AutoCloseableOracleDatabase getOracleDatabase() {
        try {
            Connection connection = dataSource.getConnection();
            return new AutoCloseableOracleDatabase(connection);
        } catch (SQLException se) {
            throw new RuntimeException(
                    "getOracleDatabase:getConnection failed", se);
        } catch (OracleException oe) {
            throw new RuntimeException(
                    "getOracleDatabase:getDatabase failed", oe);
        }
    }
    
    public OracleDatabaseTemplate getTemplate() {
        return new OracleDatabaseTemplate(this);
    }
    
}
