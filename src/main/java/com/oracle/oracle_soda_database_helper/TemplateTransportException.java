package com.oracle.oracle_soda_database_helper;

/**
 *
 */
public final class TemplateTransportException extends RuntimeException {
    private final Exception transported;
    public TemplateTransportException(final Exception t) {
        transported = t;
    }
    
    public Exception getTransported() {
        return transported;
    }
    
}
