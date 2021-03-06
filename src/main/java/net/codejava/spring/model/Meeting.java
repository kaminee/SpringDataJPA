package net.codejava.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="meeting")
public class Meeting {

	@Id
	@Column(name="meeting_id")
	@GeneratedValue//(strategy = GenerationType.IDENTITY)
	private Long meetingId;

	@Column(name="subject")
	private String subject;
	
/*	@Column(name="meeting_date")
	private Date meetingDate;
	*/
	public Meeting() {
		// TODO Auto-generated constructor stub
	}
	
	
	/*@ManyToMany(mappedBy="meetings",fetch=FetchType.EAGER)
	private Set<Employee> employees = new HashSet<Employee>();
	*/
	
	@ManyToMany(cascade = {CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name="employee_meeting", 
				joinColumns={@JoinColumn(name="meeting_id", referencedColumnName = "meeting_id")}, 
				inverseJoinColumns={@JoinColumn(name="employee_id", referencedColumnName = "employee_id")})
	private Set<Employee> employees = new HashSet<Employee>();
	
	public Meeting(String subject) {
		this.subject = subject;
//		this.meetingDate = new Date();
	}

	
	/*  public void addEmployee(Employee employee) {
	        this.employees.add(employee);
	    }*/
	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/*public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}*/

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	// Getter and Setter methods
	
	
}