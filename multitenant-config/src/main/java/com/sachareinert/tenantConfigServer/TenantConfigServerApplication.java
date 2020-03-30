package com.sachareinert.tenantConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TenantConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantConfigServerApplication.class, args);
	}

}
