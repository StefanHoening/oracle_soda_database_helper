package com.oracle.oracle_soda_database_helper;

import io.helidon.config.Config;
import io.helidon.config.ConfigValue;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public final class HelidonConfigDataSourceBuilder
        extends DataSourceBuilder<HelidonConfigDataSourceBuilder> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(
            HelidonConfigDataSourceBuilder.class);

    private static final String HELIDON_DATASOURCE_PREFIX = "javax.sql.DataSource";
    private static final String HELIDON_DATASOURCE_DRIVERCLASSNAME_SUFFIX = "driverClassName";
    private static final String HELIDON_DATASOURCE_URL_SUFFIX = "url";
    private static final String HELIDON_DATASOURCE_USER_SUFFIX = "user";
    private static final String HELIDON_DATASOURCE_PASSWORD_SUFFIX = "password";
//    private static final String HELIDON_DATASOURCE_ADDITIONAL_ELEMENTS_SUFFIX = "_additionalElements";

    private String helidonConfigPropertyName(
            final String dataSourceName,
            final String suffix) {
        String result = String.format("%s.%s.%s",
                HELIDON_DATASOURCE_PREFIX,
                dataSourceName,
                suffix);
        LOGGER.debug("helidonConfigPropertyName({}, {}) -> {}",
                dataSourceName, suffix, result);
        return result;
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
            LOGGER.debug("helidonProperty(.., {}, {}, ..)",
                    dataSourceName, suffix);
            builderAction.accept(value.get());
        }
    }


    public HelidonConfigDataSourceBuilder helidonConfig(
            final Config config, final String dataSourceName) {
        LOGGER.debug("helidonConfig(.., {}) ...", dataSourceName);
        try {
            helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_DRIVERCLASSNAME_SUFFIX,
                    (v) -> {driverClassName(v);});
            helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_URL_SUFFIX,
                    (v) -> {jdbcUrl(v);});
            helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_USER_SUFFIX,
                    (v) -> {username(v);});
            helidonProperty(config, dataSourceName, HELIDON_DATASOURCE_PASSWORD_SUFFIX,
                    (v) -> {password(v);});
            return this;
        } finally {
            LOGGER.debug("helidonConfig(.., {}) ... done.", dataSourceName);
        }
    }
}
