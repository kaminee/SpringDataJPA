package net.codejava.spring.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="doctor")
public class Doctor {

	@Id
	@Column(name="doctor_id")
	@GeneratedValue
	private Long doctorId;

	@Column(name="subject")
	private String subject;
	
/*	@Column(name="meeting_date")
	private Date meetingDate;
	*/
	@ManyToMany(mappedBy="doctors")
	private Set<Patient> patients = new HashSet<Patient>();
	
	public Doctor(String subject) {
		this.subject = subject;
//		this.meetingDate = new Date();
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}


	
	// Getter and Setter methods
	
	
}