package net.codejava.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {


	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	@Column(name="rolename")
	private String roleName;
	
	
/*	@ManyToMany(targetEntity=User.class, mappedBy="role", fetch=FetchType.EAGER)
	private Set<User> users = new HashSet<User>();
*/	
	
	@ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
	private Set<User> users = new HashSet<User>();
	
	public Role(){
		
		
	}

	public Role(int id, String roleName, Set<User> users) {
		super();
		this.id = id;
		this.roleName = roleName;
//		this.users = users;
	}

	
	/* @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
		@Fetch(FetchMode.JOIN)
	    @JoinTable(
	            name = "usersandroles",
	            joinColumns = @JoinColumn(name = "role_id"),
	            inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private Set<User> users = new HashSet<User>();*/
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


	
}
