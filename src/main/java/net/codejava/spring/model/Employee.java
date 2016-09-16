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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue
	@Column(name="employee_id")
	private Integer employeeId;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	/*@Column(name="birth_date")
	private Date birthDate;*/
	
	@Column(name="cell_phone")
	private String cellphone;

//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="id")
	
	@ManyToOne(fetch=FetchType.EAGER,optional = false)
	@JoinColumn(name = "company_id")
	private Company company;
	
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="employee_meeting", 
				joinColumns={@JoinColumn(name="employee_id")}, 
				inverseJoinColumns={@JoinColumn(name="meeting_id")})
	private Set<Meeting> meetings = new HashSet<Meeting>();
	
	public Employee() {
		
	}
	
	public Employee(String firstname, String lastname, String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
//		this.birthDate = new Date(System.currentTimeMillis());
		this.cellphone = phone;
	}

	

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	// Getter and Setter methods
}
