package asd.syrotenko.propertyplaceholder.example;

import java.util.Properties;

public class DbConfig {
	private Properties dbProperties;

	public DbConfig(Properties dbProperties) {
		this.dbProperties = dbProperties;
	}

	public String getDbServer() {
		return dbProperties.getProperty("server");
	}

	public String getDbPort() {
		return dbProperties.getProperty("port");
	}

	public String getDbUser() {
		return dbProperties.getProperty("user");
	}

	public String getDbPassword() {
		return dbProperties.getProperty("password");
	}
}
