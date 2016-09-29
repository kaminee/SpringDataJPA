package net.codejava.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.spring.model.Role;
import net.codejava.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String name);

	@Query("")
	List<Role> findRolesByUsername(String name);
	

}
