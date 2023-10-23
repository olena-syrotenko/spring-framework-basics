package asd.syrotenko.lifecycle.example;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class PatientInterface implements InitializingBean, DisposableBean {

	public static final String LOF_FILE = "src/main/resources/lifecycle/patientInterfaceLog.txt";
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		try {
			String logMessage = LocalDate.now() + " Id was set for Patient bean\n";
			Files.write(Paths.get(LOF_FILE), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.out.println("Exception occurred when writing setId log into " + LOF_FILE);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			String logMessage = LocalDate.now() + " Patient bean was created with InitializingBean interface method\n";
			Files.write(Paths.get(LOF_FILE), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Exception occurred when writing afterPropertiesSet log into " + LOF_FILE);
		}
	}

	@Override
	public void destroy() throws Exception {
		try {
			String logMessage = LocalDate.now() + " Patient bean was destroyed with DisposableBean interface method\n";
			Files.write(Paths.get(LOF_FILE), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Exception occurred when writing destroy log into " + LOF_FILE);
		}
	}
}
