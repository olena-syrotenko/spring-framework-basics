package asd.syrotenko.autowiring.assignment;

import java.time.LocalDate;

public class Reservation {
	private Integer id;
	private LocalDate fromDate;
	private LocalDate toDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = LocalDate.parse(fromDate);
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = LocalDate.parse(toDate);
	}
}
