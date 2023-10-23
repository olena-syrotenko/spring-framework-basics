package asd.syrotenko;

import asd.syrotenko.propertyplaceholder.assignment.ClientConfig;
import asd.syrotenko.propertyplaceholder.example.DbConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyBeanTest {

	private ClassPathXmlApplicationContext ctx;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("propertyBeanConfig.xml");
	}

	@Test
	public void testDbConfigBean() {
		DbConfig dbConfig = (DbConfig) ctx.getBean("dbConfig");
		assertEquals("exampleserver", dbConfig.getDbServer());
		assertEquals("3306", dbConfig.getDbPort());
		assertEquals("helen", dbConfig.getDbUser());
		assertEquals("test", dbConfig.getDbPassword());
	}

	@Test
	public void testClientConfigBean() {
		ClientConfig clientConfig = (ClientConfig) ctx.getBean("clientConfig");
		assertEquals("test-xyz", clientConfig.getUrl());
		assertEquals("test", clientConfig.getUserName());
		assertEquals("test", clientConfig.getPassword());
	}
}
