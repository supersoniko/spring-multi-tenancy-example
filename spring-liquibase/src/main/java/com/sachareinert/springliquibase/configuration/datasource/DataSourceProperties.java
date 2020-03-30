package com.sachareinert.springliquibase.configuration.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tenants")
@RefreshScope
public class DataSourceProperties {

    private Map<Object, Object> datasources = new LinkedHashMap<>();

    public Map<Object, Object> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, Map<String, String>> datasources) {
        datasources
                .forEach((key, value) -> this.datasources.put(key, convert(value)));
    }

    public DataSource convert(Map<String, String> source) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(source.get("url"));
        config.setDriverClassName(source.get("driverClassName"));
        config.setUsername(source.get("username"));
        config.setPassword(source.get("password"));
        config.setMaximumPoolSize(Integer.parseInt(source.get("maximum-pool-size")));
        config.setPoolName(source.get("pool-name"));

        return new HikariDataSource(config);
    }
}
