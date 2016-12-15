package net.codejava.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="hotel_booking")
public class HotelBooking extends Booking{


	@Column(name="room_type")
	private String roomType;
	
	@Column(name="room_fare")
	private Long roomFare;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;

	@Column(name="total_fare")
	private Long totalFare;
	
	
	// Getter and Setter methods #############################################

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Long getRoomFare() {
		return roomFare;
	}

	public void setRoomFare(Long roomFare) {
		this.roomFare = roomFare;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Long totalFare) {
		this.totalFare = totalFare;
	}

}