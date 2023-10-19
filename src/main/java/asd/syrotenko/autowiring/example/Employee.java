package asd.syrotenko.autowiring.example;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private Integer id;
	private Address address;
	private List<String> equipment = new ArrayList<>();

	public Employee() {
	}

	public Employee(Address address) {
		this.address = address;
		equipment.add("Lenovo Laptop");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<String> equipment) {
		this.equipment = equipment;
	}
}
