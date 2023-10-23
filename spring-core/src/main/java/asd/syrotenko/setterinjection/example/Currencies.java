package asd.syrotenko.setterinjection.example;

import java.util.Set;

public class Currencies {

	private Set<String> availableCurrencies;

	public Set<String> getAvailableCurrencies() {
		return availableCurrencies;
	}

	public void setAvailableCurrencies(Set<String> availableCurrencies) {
		this.availableCurrencies = availableCurrencies;
	}
}
