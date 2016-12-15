package net.codejava.spring.model;

public class Address {


	private Integer adressId;
	private String lane;
	private String city;
	private String state;
	private String country;

	Integer getAdressId() {
		return adressId;
	}


	public void setAdressId(Integer adressId) {
		this.adressId = adressId;
	}


	public String getLane() {
		return lane;
	}


	public void setLane(String lane) {
		this.lane = lane;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	
	
}
