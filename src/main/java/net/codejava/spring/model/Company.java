package net.codejava.spring.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	 	@Id
	    @GeneratedValue
		@Column(name = "id")
	    private Integer id;
	 
	 	@Column(name = "name")
	    private String name;
	 
	    @Column(name = "pan_number")
	    private String panNumber;
	    
	    @Column(name = "active")
	    private boolean active;
	    
	    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER,mappedBy = "company", orphanRemoval = true)
		private Collection<Employee> employees;
	    
	    public Integer getId() {
	        return id;
	    }
	 
	    public void setId(Integer id) {
	        this.id = id;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	 
		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public Collection<Employee> getEmployees() {
			return employees;
		}

		public void setEmployees(Collection<Employee> employees) {
			this.employees = employees;
		}

	
	    
}
