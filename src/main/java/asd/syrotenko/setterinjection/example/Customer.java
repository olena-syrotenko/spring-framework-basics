package asd.syrotenko.setterinjection.example;

import java.util.Map;

public class Customer {
	private Integer id;
	private Map<Integer, String> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Integer, String> getProducts() {
		return products;
	}

	public void setProducts(Map<Integer, String> products) {
		this.products = products;
	}
}
