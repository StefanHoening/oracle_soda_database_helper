package com.oracle.oracle_soda_database_helper;

import java.sql.SQLException;

/**
 * A {@code SqlFunction} is similar to a {@code java.util.function.Function} but
 * allows to throw an {@code SQLException}.
 * 
 * @param <T> the type of <b>SQL</b> object, where this function is applied on
 * @param <R> the return type
 * @author stefan
 */
public interface SqlFunction<T, R> {
    R apply(T sqlObject) throws SQLException;
}
