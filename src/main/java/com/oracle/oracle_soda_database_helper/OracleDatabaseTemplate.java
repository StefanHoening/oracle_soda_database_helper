package com.oracle.oracle_soda_database_helper;

import java.util.function.BiConsumer;
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
    
    public void executeConsumer(
            final Consumer<OracleDatabase> consumer) throws Exception {
        try (AutoCloseableOracleDatabase db = provider.getOracleDatabase()) {
            consumer.accept(db);
        } catch (TemplateTransportException tte) {
            throw tte.getTransported();
        }
    }
    
    public <U> void executeBiConsumer(
            final BiConsumer<OracleDatabase, U> consumer, final U arg)
            throws Exception {
        try (AutoCloseableOracleDatabase db = provider.getOracleDatabase()) {
            consumer.accept(db, arg);
        } catch (TemplateTransportException tte) {
            throw tte.getTransported();
        }
    }
    
    public <R> R executeFunction(final Function<OracleDatabase, R> function)
            throws Exception {
        try (AutoCloseableOracleDatabase db = provider.getOracleDatabase()) {
            return function.apply(db);
        } catch (TemplateTransportException tte) {
            throw tte.getTransported();
        }
    }
    
    public <U, R> R executeBiFunction(
            final BiFunction<OracleDatabase, U, R> function, final U arg)
            throws Exception {
        try (AutoCloseableOracleDatabase db = provider.getOracleDatabase()) {
            return function.apply(db, arg);
        } catch (TemplateTransportException tte) {
            throw tte.getTransported();
        }
    }
    
}
