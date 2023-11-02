package asd.syrotenko.service;

import asd.syrotenko.aspect.LoggingAspect;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyServiceTest {

	private static final String DATE = LocalDate.now().toString();

	private ClassPathXmlApplicationContext ctx;
	private Random random;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("beanConfig.xml");
		random = new Random();
	}

	@Test
	public void testMultiply() throws IOException {
		MultiplyService multiplyService = (MultiplyService) ctx.getBean("multiplyService");

		Integer num1 = random.nextInt(1000);
		Integer num2 = random.nextInt(1000);
		Integer result = multiplyService.multiply(num1, num2);
		assertEquals(num1 * num2, result);

		String expectedLogContent = "Try to call class asd.syrotenko.service.MultiplyService#multiply with parameters: " + num1 + ", " + num2 + "\n"
				+ "Execute method class asd.syrotenko.service.MultiplyService#multiply with parameters: " + num1 + ", " + num2 + "\n";
		String logFileContent = FileUtils.readFileToString(new File(LoggingAspect.LOF_FILES_PATH + "log_" + DATE + "_MultiplyService#multiply"), "UTF-8");
		assertEquals(expectedLogContent, logFileContent);
	}

}