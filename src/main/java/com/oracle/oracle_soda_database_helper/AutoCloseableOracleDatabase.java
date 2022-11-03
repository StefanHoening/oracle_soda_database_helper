package com.oracle.oracle_soda_database_helper;

import java.sql.Connection;
import oracle.soda.OracleCollection;

import oracle.soda.OracleDatabase;
import oracle.soda.OracleDatabaseAdmin;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;
import oracle.soda.rdbms.OracleRDBMSClient;

/**
 * An {@code AutoCloseableOracleDatabase} wraps around an {@code OracleDatabase}
 * instance and its underlying {@code Connection}, as an {@code AutoCloseable}.
 */
public class AutoCloseableOracleDatabase implements OracleDatabase, AutoCloseable {
    
    private final OracleDatabase oracleDatabase;
    private final Connection connection;

    public AutoCloseableOracleDatabase(final Connection theConnection)
            throws OracleException {
        connection = theConnection;
        OracleRDBMSClient client = new OracleRDBMSClient();
        oracleDatabase = client.getDatabase(connection);
    }    
    
    @Override
    public OracleCollection openCollection(String string) throws OracleException {
        return oracleDatabase.openCollection(string);
    }

    @Override
    public OracleDatabaseAdmin admin() {
        return oracleDatabase.admin();
    }

    @Override
    public OracleDocument createDocumentFromString(String string) throws OracleException {
        return oracleDatabase.createDocumentFromString(string);
    }

    @Override
    public OracleDocument createDocumentFromString(String string, String string1) throws OracleException {
        return oracleDatabase.createDocumentFromString(string, string1);
    }

    @Override
    public OracleDocument createDocumentFromString(String string, String string1, String string2) throws OracleException {
        return oracleDatabase.createDocumentFromString(string, string1, string2);
    }

    @Override
    public OracleDocument createDocumentFrom(Object o) throws OracleException {
        return oracleDatabase.createDocumentFrom(o);
    }

    @Override
    public OracleDocument createDocumentFrom(String string, Object o) throws OracleException {
        return oracleDatabase.createDocumentFrom(string, o);
    }

    @Override
    public OracleDocument createDocumentFromByteArray(byte[] bytes) throws OracleException {
        return oracleDatabase.createDocumentFromByteArray(bytes);
    }

    @Override
    public OracleDocument createDocumentFromByteArray(String string, byte[] bytes) throws OracleException {
        return oracleDatabase.createDocumentFromByteArray(string, bytes);
    }

    @Override
    public OracleDocument createDocumentFromByteArray(String string, byte[] bytes, String string1) throws OracleException {
        return oracleDatabase.createDocumentFromByteArray(string, bytes, string1);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
    
}
