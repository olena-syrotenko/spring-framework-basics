package asd.syrotenko.lifecycle.assignment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class TicketReservation {
	public static final String LOF_FILE = "src/main/resources/lifecycle/ticketReservationLog.txt";

	@PostConstruct
	public void initialize() {
		try {
			String logMessage = LocalDate.now() + " TicketReservation bean was created with annotation\n";
			Files.write(Paths.get(LOF_FILE), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.out.println("Exception occurred when writing initialize TicketReservation log into " + LOF_FILE);
		}
	}

	@PreDestroy
	public void cleanUp() {
		try {
			String logMessage = LocalDate.now() + " TicketReservation bean was destroyed with annotation\n";
			Files.write(Paths.get(LOF_FILE), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Exception occurred when writing cleanUp TicketReservation log into " + LOF_FILE);
		}
	}
}
