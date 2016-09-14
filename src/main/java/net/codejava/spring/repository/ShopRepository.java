package net.codejava.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.spring.model.Company;

public interface ShopRepository extends JpaRepository<Company, Integer>{
	List<Company> findByName(String name);
	
//	@Transactional
//	@Query("select * from shops s order by s.id asc")
	List<Company> OrderByPanNumber();
	
	@Query("select s from Company s order by s.id asc")
	List<Company> OrderByIdDescending();
	
	
	List<Company> findTop2ByOrderByPanNumberDesc();

}
