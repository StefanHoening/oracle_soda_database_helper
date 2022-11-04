package com.oracle.oracle_soda_database_helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

import javax.sql.DataSource;
import oracle.soda.OracleCollection;
import oracle.soda.OracleDocument;

import oracle.soda.OracleException;

/**
 *
 */
public class OracleDatabaseProvider {
    
    private final DataSource dataSource;
    
    public OracleDatabaseProvider(final DataSource theDataSource) {
        dataSource = theDataSource;
    }
    
    public void initializeCollection(
            final String collectionName,
            final OracleDocument metaData,
            final Consumer<OracleCollection> collectionInitializer)
            throws Exception {
        try (AutoCloseableOracleDatabase database = getOracleDatabase()) {
            OracleCollection collection = database.openCollection(collectionName);
            if (collection == null) {
                // we need to create and initialze the collection
                if (metaData == null) {
                    collection = database.admin().createCollection(collectionName);
                } else {
                    collection = database.admin().createCollection(collectionName, metaData);
                }
                if (collectionInitializer != null) {
                    collectionInitializer.accept(collection);
                }
            }
        }
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
