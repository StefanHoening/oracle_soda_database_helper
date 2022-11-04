package com.oracle.oracle_soda_database_helper;

import io.helidon.config.Config;
import io.helidon.config.ConfigValue;

import java.util.function.Consumer;

/**
 *
 */
public final class HelidonConfigDataSourceBuilder
        extends DataSourceBuilder<HelidonConfigDataSourceBuilder> {

    private static final String HELIDON_DATASOURCE_PREFIX = "javax.sql.DataSource";
    private static final String HELIDON_DATASOURCE_DRIVERCLASSNAME_SUFFIX = "driverClassName";
    private static final String HELIDON_DATASOURCE_URL_SUFFIX = "url";
    private static final String HELIDON_DATASOURCE_USER_SUFFIX = "user";
    private static final String HELIDON_DATASOURCE_PASSWORD_SUFFIX = "password";
//    private static final String HELIDON_DATASOURCE_ADDITIONAL_ELEMENTS_SUFFIX = "_additionalElements";

    private String helidonConfigPropertyName(
            final String dataSourceName,
            final String suffix) {
        return String.format("%s.%s.%s",
                HELIDON_DATASOURCE_PREFIX,
                dataSourceName,
                suffix);
    }
    
    private void helidonProperty(
            final Config config,
            final String dataSourceName,
            final String suffix,
            final Consumer<String> builderAction) {
        ConfigValue<String> value = config.get(
                helidonConfigPropertyName(
                        dataSourceName,
                        suffix))
                .asString();
        if (value.isPresent()) {
            builderAction.accept(value.get());
        }
    }


    public HelidonConfigDataSourceBuilder helidonConfig(
            final Config config, final String dataSourceName) {
        helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_DRIVERCLASSNAME_SUFFIX,
                (v) -> {jdbcUrl(v);});
        helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_URL_SUFFIX,
                (v) -> {jdbcUrl(v);});
        helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_USER_SUFFIX,
                (v) -> {username(v);});
        helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_PASSWORD_SUFFIX,
                (v) -> {password(v);});
        return this;
    }
}
