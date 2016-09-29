package net.codejava.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component

@Entity
@Table(name = "patient")
public class Patient implements Serializable{
	
	
	@Id 
	@GeneratedValue
	@Column(name = "patient_id")
	private int id;
	
	@Column(name = "username")
	private String username;
	 
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="patinet_doctor", 
				joinColumns={@JoinColumn(name="patient_id")}, 
				inverseJoinColumns={@JoinColumn(name="doctor_id")})
	private Set<Doctor> doctors = new HashSet<Doctor>();
	
	public Patient(){}
	
	private long countryId;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}*/

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}


	

	
}
