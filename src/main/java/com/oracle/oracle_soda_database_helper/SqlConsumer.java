package com.oracle.oracle_soda_database_helper;

import java.sql.SQLException;


/**
 * A {@code SqlConsumer} is similar to a {@code java.util.function.Consumer} but
 * allows to throw an {@code SQLException}.
 * 
 * @param <T> the type of <b>SQL</b> object, where this function is applied on
 * @author stefan
 */
public interface SqlConsumer<T> {
    void accept(T sqlObject) throws SQLException;
}
