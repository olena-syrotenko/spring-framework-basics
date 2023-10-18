package asd.syrotenko.constructorinjection;

public class NumDifference {
	private Integer firstValue;
	private Integer secondValue;

	public NumDifference(Integer firstValue, Integer secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	public Integer getDifference() {
		return firstValue - secondValue;
	}
}
