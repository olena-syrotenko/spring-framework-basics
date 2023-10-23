package asd.syrotenko.lifecycle.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class PatientXml {

	public static final String LOF_FILE = "src/main/resources/lifecycle/patientXmlLog.txt";
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

	public void createBean() {
		try {
			String logMessage = LocalDate.now() + " Patient bean was created with xml config\n";
			Files.write(Paths.get(LOF_FILE), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Exception occurred when writing createBean log into " + LOF_FILE);
		}
	}

	public void destroyBean() {
		try {
			String logMessage = LocalDate.now() + " Patient bean was destroyed with xml config\n";
			Files.write(Paths.get(LOF_FILE), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Exception occurred when writing destroyBean log into " + LOF_FILE);
		}
	}
}
