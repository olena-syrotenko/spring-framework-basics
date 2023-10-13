package asd.syrotenko.setterinjection.example;

import java.util.Properties;

public class Languages {
	private Properties countryAndLanguages;

	public Properties getCountryAndLanguages() {
		return countryAndLanguages;
	}

	public void setCountryAndLanguages(Properties countryAndLanguages) {
		this.countryAndLanguages = countryAndLanguages;
	}
}
