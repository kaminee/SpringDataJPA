package net.codejava.spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.Employee;
import net.codejava.spring.model.Meeting;
import net.codejava.spring.repository.MeetingRepository;
import net.codejava.spring.services.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService{

	@Resource
    private MeetingRepository meetingRepository;
 
    @Override
    @Transactional
    public Meeting create(Meeting shop) {
    	Meeting createdShop = shop;
    	for (Employee emp : shop.getEmployees()) {
    		createdShop.getEmployees().add(emp);
		}
//    	createdShop.getEmployees().add(shop.get)

        return meetingRepository.save(createdShop);
    }
     
    @Override
    @Transactional
    public Meeting delete(long id) {
    	System.out.println("=========id====="+id);
//    	String meetingStr=String.valueOf(id);
//    	Integer meetingId=Integer.parseInt(meetingStrs);
    	Meeting deletedShop = (Meeting) meetingRepository.findByMeetingId(id);
    	meetingRepository.delete(deletedShop);
        return deletedShop;
    }
 
    @Override
    @Transactional
    public List<Meeting> findAll() {
        return meetingRepository.findAll();
    }
 
    @Override
    @Transactional
    public Meeting update(Meeting shop){
        return meetingRepository.save(shop);
    }
/*
	@Override
	public Meeting findById(long id) {
		// TODO Auto-generated method stub
		 
	}*/

}
