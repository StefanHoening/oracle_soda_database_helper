package com.oracle.oracle_soda_database_helper;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import oracle.soda.OracleDatabase;

/**
 *
 */
public final class OracleDatabaseTemplate {
    
    private final OracleDatabaseProvider provider;
    
    OracleDatabaseTemplate(final OracleDatabaseProvider theProvider) {
        provider = theProvider;
    }
    
    public void executeConsumer(final Consumer<OracleDatabase> consumer) throws Exception {
        try (AutoCloseableOracleDatabase db = provider.getOracleDatabase()) {
            consumer.accept(db);
        }
    }
    
    public <T> T executeFunction(final Function<OracleDatabase, T> function) throws Exception {
        try (AutoCloseableOracleDatabase db = provider.getOracleDatabase()) {
            return function.apply(db);
        }
    }
    
    public <T, R> R executeBiFunction(final BiFunction<OracleDatabase, T, R> function, final T arg) throws Exception {
        try (AutoCloseableOracleDatabase db = provider.getOracleDatabase()) {
            return function.apply(db, arg);
        }
    }
    
}
