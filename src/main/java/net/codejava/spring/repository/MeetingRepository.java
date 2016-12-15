package net.codejava.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.codejava.spring.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer>,JpaSpecificationExecutor{
//		@Query("select u from User u where u.firstname like %?1")
	  Meeting findByMeetingId(long meetingId);
}
