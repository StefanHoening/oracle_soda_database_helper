package com.oracle.oracle_soda_database_helper;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import java.util.function.Consumer;

import oracle.soda.OracleCollection;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class OracleDatabaseProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleDatabaseProvider.class);
    
    private final DataSource dataSource;
    
    public OracleDatabaseProvider(final DataSource theDataSource) {
        dataSource = theDataSource;
    }
    
    public void initializeCollection(
            final String collectionName,
            final String metaDataDocument,
            final Consumer<OracleCollection> collectionInitializer)
            throws Exception {
        LOGGER.info("initializeCollection({}, ...) ... ", collectionName);
        try (AutoCloseableOracleDatabase database = getOracleDatabase()) {
            OracleCollection collection = database.openCollection(collectionName);
            if (collection == null) {
                LOGGER.debug("initializeCollection(...) creating missing collection");
                // we need to create and initialze the collection
                if (metaDataDocument == null) {
                    collection = database.admin().createCollection(collectionName);
                } else {
                    OracleDocument metaData = database.createDocumentFromString(metaDataDocument);
                    collection = database.admin().createCollection(collectionName, metaData);
                }
                if (collectionInitializer != null) {
                    collectionInitializer.accept(collection);
                }
            }
        } finally {
            LOGGER.info("initializeCollection({}, ...) ... done.", collectionName);
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
