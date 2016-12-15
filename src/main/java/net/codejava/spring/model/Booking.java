package net.codejava.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {

	@Id
	@Column(name="booking_id")
	@GeneratedValue
	private Long bookingId;

	@Column(name="booking_type")
	private String bookingType;
	
	@Column(name="client_id")
	private Date clientId;
	
	@Column(name="fare")
	private Long fare;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public Date getClientId() {
		return clientId;
	}

	public void setClientId(Date clientId) {
		this.clientId = clientId;
	}

	public Long getFare() {
		return fare;
	}

	public void setFare(Long fare) {
		this.fare = fare;
	}

	
	
	
	// Getter and Setter methods
	
	
}