package net.codejava.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import net.codejava.spring.dao.GuiFormService;
import net.codejava.spring.model.GuiForm;
import net.codejava.spring.model.Meeting;
import net.codejava.spring.services.MeetingService;

/**
 * Handles requests for the application home page.
 */
@RequestMapping(value = "/meeting")
@Controller
public class MeetingController {
	
	@Autowired
	private MeetingService meetingService;
	

	@Autowired
	private GuiFormService guiFormService;
	
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<Meeting>  listOfUsers() {      
		List<Meeting> listUsers = meetingService.findAll();
	    return listUsers;
	}
	
	/* @RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      return "meeting";
	   }*/
	 
	  @RequestMapping(value = "create", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody Meeting meeting,UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Meeting " + meeting.getSubject());
	        System.out.println("meeting emp size "+meeting.getEmployees().size());
//	        Set<Employee> empList=meeting.getEmployees();
//	        meeting.setEmployees(empList);
	        
	        meetingService.create(meeting);
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	  
	  
	   @RequestMapping(value = "/list", method = RequestMethod.GET)
	    public ResponseEntity<List<Meeting>> listAllMeeting() {
			List<Meeting> employees = meetingService.findAll();
	        if(employees.isEmpty()){
	            return new ResponseEntity<List<Meeting>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Meeting>>(employees, HttpStatus.OK);
	    }
	   
	   
	    //------------------- Update a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Meeting> updateMeeting(@PathVariable("id") long id, @RequestBody Meeting meeting) {
	        System.out.println("Updating Meeting " + id);
	        meetingService.update(meeting);
	        return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/remove/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Meeting> deMeeting(@PathVariable("id") long id) {
	        System.out.println("DELETE Meeting " + id);
	        meetingService.delete(id);
	        return new ResponseEntity<Meeting>(HttpStatus.OK);
	    }
	    
		 @RequestMapping(value = "/loadUsers", method = RequestMethod.GET)
		   public String printHello1(ModelMap model) {
		      model.addAttribute("message", "Hello Spring MVC Framework!");
		      return "meeting";
		   }
		 
			@RequestMapping(value = "/byformtype/{type}", method = RequestMethod.GET)
			@ResponseBody
			public List<GuiForm>  listOfFieldsByType(@PathVariable("type") String type) {      
				List<GuiForm> listFormFields = guiFormService.findByGuiType(type);
			    return listFormFields;
			}
}
