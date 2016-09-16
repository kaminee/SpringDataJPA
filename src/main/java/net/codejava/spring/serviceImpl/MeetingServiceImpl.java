package net.codejava.spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return meetingRepository.save(createdShop);
    }
     
    @Override
    @Transactional
    public Meeting delete(int id) {
    	Meeting deletedShop = meetingRepository.findOne(id);
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

}
