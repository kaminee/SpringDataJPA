package net.codejava.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.spring.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findByFirstname(String name);
	
//	@Transactional
//	@Query("select * from shops s order by s.id asc")
/*	List<Employee> OrderByPanNumber();
	
	@Query("select s from Company s order by s.id asc")
	List<Employee> OrderByIdDescending();
	
	
	List<Employee> findTop2ByOrderByPanNumberDesc();*/

}
