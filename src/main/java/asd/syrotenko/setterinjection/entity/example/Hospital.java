package asd.syrotenko.setterinjection.entity.example;

import java.util.List;

public class Hospital {
	private String name;
	List<String> departments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}
}
