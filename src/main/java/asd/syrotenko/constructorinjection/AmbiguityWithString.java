package asd.syrotenko.constructorinjection;

public class AmbiguityWithString {
	private Integer intField;
	private Double doubleField;
	private String stringField;

	public AmbiguityWithString(Double doubleField) {
		this.doubleField = doubleField;
	}

	public AmbiguityWithString(Integer intField) {
		this.intField = intField;
	}

	public AmbiguityWithString(String stringField) {
		this.stringField = stringField;
	}

	public Integer getIntField() {
		return intField;
	}

	public void setIntField(Integer intField) {
		this.intField = intField;
	}

	public Double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(Double doubleField) {
		this.doubleField = doubleField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}
}
