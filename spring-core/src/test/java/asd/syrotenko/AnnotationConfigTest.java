package asd.syrotenko;

import asd.syrotenko.annotationconfig.ExampleService;
import asd.syrotenko.annotationconfig.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationConfigTest {
	private AnnotationConfigApplicationContext ctx;
	private ExampleService exampleService;

	@BeforeEach
	public void init() {
		ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		exampleService = ctx.getBean(ExampleService.class);
	}

	@Test
	public void testSaveWithEmptyString() {
		assertEquals("No message was provided", exampleService.saveMessage(""));
	}

	@Test
	public void testSave() {
		assertEquals("Create: test message", exampleService.saveMessage("test message"));
	}
}
