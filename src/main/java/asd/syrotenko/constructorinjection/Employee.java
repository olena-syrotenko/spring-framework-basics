package asd.syrotenko.constructorinjection;

import java.util.List;

public class Employee {
	private Integer id;
	private Address address;
	private List<String> equipment;

	public Employee(Integer id, Address address, List<String> equipment) {
		this.id = id;
		this.address = address;
		this.equipment = equipment;
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
