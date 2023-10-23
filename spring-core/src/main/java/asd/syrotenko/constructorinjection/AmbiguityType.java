package asd.syrotenko.constructorinjection;

public class AmbiguityType {
	private int intField;
	private double doubleField;

	public AmbiguityType(double doubleField) {
		this.doubleField = doubleField;
	}

	public AmbiguityType(int intField) {
		this.intField = intField;
	}

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(double doubleField) {
		this.doubleField = doubleField;
	}

}
