package com.api;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.context.annotation.Configuration;
//import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;

@Configuration
public class DataConfig {
	
	// JdbcOperations needed -> see page..
	@Bean
	public DataSource dataSource() {
		System.out.println("datasource initiated.");
		System.out.println("Creating datasource...");
		System.out.flush();
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setName("h2dbase")
				.addScript("schema.sql") // FIXME: How do I reference this file?
				.build();
	}
	
	@Bean
	public JdbcOperations jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	// Extra two methods:
	/*@Bean(initMethod="start", destroyMethod="stop")
	public Server h2WebServer() throws SQLException {
		Server server = Server.createWebServer(
				"-web", "-webAllowOthers", "-webPort", "8082");
		return server;
		
	}
	
	@Bean(initMethod="start", destroyMethod="stop")
	@DependsOn("h2WebServer")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp","-tcpAllowOthers","-tcpPort","9092");
	}*/
	
}
