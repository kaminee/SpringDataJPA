package net.codejava.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component

@Entity
@Table(name = "USERS")
public class User implements Serializable{
	
	
	@Id 
	@GeneratedValue
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "username")
	private String username;
	 
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
/*	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY )
    @JoinTable(name="COUNTRY",
        joinColumns = @JoinColumn(name="country_id"),
        inverseJoinColumns = @JoinColumn(name="country_id")
    )*/
/*	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	*/
	
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
