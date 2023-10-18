package asd.syrotenko.constructorinjection;

public class Address {
	private Integer hno;
	private String street;
	private String city;

	public Address(Integer houseNumber, String street, String city) {
		this.hno = houseNumber;
		this.street = street;
		this.city = city;
	}

	public Integer getHno() {
		return hno;
	}

	public void setHno(Integer hno) {
		this.hno = hno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
