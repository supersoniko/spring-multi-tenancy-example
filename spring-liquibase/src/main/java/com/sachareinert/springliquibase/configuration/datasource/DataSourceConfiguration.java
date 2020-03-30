package com.sachareinert.springliquibase.configuration.datasource;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private final DataSourceProperties dataSourceProperties;

    public DataSourceConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Primary
    @Bean
    @RefreshScope
    public DataSource dataSource() {
        TenantDataSource customDataSource = new TenantDataSource();
        customDataSource.setTargetDataSources(dataSourceProperties.getDatasources());
        return customDataSource;
    }
}
