package com.sachareinert.springliquibase.dtos;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
