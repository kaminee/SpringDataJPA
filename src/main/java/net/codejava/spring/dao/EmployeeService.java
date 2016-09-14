package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Company;
import net.codejava.spring.model.Employee;

public interface EmployeeService {
	public Employee create(Employee shop);
    public Employee delete(int id);
    public List<Employee> findAll();
    public Employee update(Employee shop);
//    public Employee findById(int id);
    public List<Employee> findByFirstname(String name);
//    public List<Employee> OrderByPanNumber();
//	List<Employee> OrderByIdDescending();


}
