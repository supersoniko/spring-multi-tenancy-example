package com.sachareinert.springliquibase.configuration.datasource;

import com.sachareinert.springliquibase.configuration.web.TenantStorage;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantStorage.getTenantId();
    }
}
