package asd.syrotenko.service;

import java.util.Objects;

public class MultiplyService {

	public Integer multiply(Integer num1, Integer num2) {
		if (num1 == null || num2 == null) {
			return null;
		}
		return num1 * num2;
	}
}
