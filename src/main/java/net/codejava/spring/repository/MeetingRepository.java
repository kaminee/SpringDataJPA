package net.codejava.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.spring.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer>{
	
}
