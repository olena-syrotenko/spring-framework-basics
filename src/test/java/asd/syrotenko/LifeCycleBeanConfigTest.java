package asd.syrotenko;

import asd.syrotenko.lifecycle.assignment.TicketReservation;
import asd.syrotenko.lifecycle.example.PatientAnnotation;
import asd.syrotenko.lifecycle.example.PatientInterface;
import asd.syrotenko.lifecycle.example.PatientXml;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LifeCycleBeanConfigTest {
	private static final String DATE = LocalDate.now().toString();
	private ClassPathXmlApplicationContext ctx;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("lifeCycleBeanConfig.xml");
	}

	@Test
	public void testPatientBeanWithXmlConfig() throws IOException {
		PatientXml patientXml = (PatientXml) ctx.getBean("patientXml");
		assertEquals(1, patientXml.getId());
		ctx.close();

		String expectedFileLogContent = DATE + " Id was set for Patient bean\n" + DATE + " Patient bean was created with xml config\n" + DATE
				+ " Patient bean was destroyed with xml config\n";
		String logFileContent = FileUtils.readFileToString(new File(PatientXml.LOF_FILE), "UTF-8");
		assertEquals(expectedFileLogContent, logFileContent);
	}

	@Test
	public void testPatientBeanWithInterfaceMethods() throws IOException {
		PatientInterface patientInterface = (PatientInterface) ctx.getBean("patientInterface");
		assertEquals(2, patientInterface.getId());
		ctx.close();

		String expectedFileLogContent =
				DATE + " Id was set for Patient bean\n" + DATE + " Patient bean was created with InitializingBean interface method\n" + DATE
						+ " Patient bean was destroyed with DisposableBean interface method\n";
		String logFileContent = FileUtils.readFileToString(new File(PatientInterface.LOF_FILE), "UTF-8");
		assertEquals(expectedFileLogContent, logFileContent);
	}

	@Test
	public void testPatientBeanWithAnnotations() throws IOException {
		PatientAnnotation patientAnnotation = (PatientAnnotation) ctx.getBean("patientAnnotation");
		assertEquals(3, patientAnnotation.getId());
		ctx.close();

		String expectedFileLogContent = DATE + " Id was set for Patient bean\n" + DATE + " Patient bean was created with annotation\n" + DATE
				+ " Patient bean was destroyed with annotation\n";
		String logFileContent = FileUtils.readFileToString(new File(PatientAnnotation.LOF_FILE), "UTF-8");
		assertEquals(expectedFileLogContent, logFileContent);
	}

	@Test
	public void testTicketReservationBean() throws IOException {
		TicketReservation ticketReservation = (TicketReservation) ctx.getBean("ticketReservation");
		assertNotNull(ticketReservation);
		ctx.close();

		String expectedFileLogContent =
				DATE + " TicketReservation bean was created with annotation\n" + DATE + " TicketReservation bean was destroyed with annotation\n";
		String logFileContent = FileUtils.readFileToString(new File(TicketReservation.LOF_FILE), "UTF-8");
		assertEquals(expectedFileLogContent, logFileContent);
	}
}
