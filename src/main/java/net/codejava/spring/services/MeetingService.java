package net.codejava.spring.services;

import java.util.List;

import net.codejava.spring.model.Meeting;

public interface MeetingService {
	public Meeting create(Meeting shop);
    public Meeting delete(long id);
    public List<Meeting> findAll();
    public Meeting update(Meeting shop);
//    public Meeting findById(long id);
//    public List<Employee> findByFirstname(String name);
//    public List<Employee> OrderByPanNumber();
//	List<Employee> OrderByIdDescending();


}
