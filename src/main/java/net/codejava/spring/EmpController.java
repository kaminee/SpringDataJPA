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

import net.codejava.spring.dao.EmployeeService;
import net.codejava.spring.model.Employee;

/**
 * Handles requests for the application home page.
 */
@RequestMapping(value = "/employee")
@Controller
public class EmpController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee>  listOfUsers() {      
		List<Employee> listUsers = employeeService.findAll();
	    return listUsers;
	}
	
	 @RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");

	      return "employee";
	   }
	 
	  @RequestMapping(value = "create", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody Employee employee,UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Emp " + employee.getFirstname());
	 
	        employeeService.create(employee);
	 
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	  
	  
	   @RequestMapping(value = "/emp", method = RequestMethod.GET)
	    public ResponseEntity<List<Employee>> listAllUsers() {
			List<Employee> employees = employeeService.findAll();
	        if(employees.isEmpty()){
	            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	    }
	   
	   
	    //------------------- Update a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Employee> updateUser(@PathVariable("id") long id, @RequestBody Employee user) {
	        System.out.println("Updating User " + id);
	         
	      /*  User currentUser = employeeService.
	        System.out.println("currentUser " + currentUser+"\t -->"+user.getFirstname());
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }*/
	 
	      /*  currentUser.setUsername(user.getUsername());
	        currentUser.setAddress(user.getAddress());
	        currentUser.setEmail(user.getEmail());*/
	         
	        employeeService.update(user);
	        return new ResponseEntity<Employee>(user, HttpStatus.OK);
	    }
	    
	    
		 @RequestMapping(value = "/loadUsers", method = RequestMethod.GET)
		   public String printHello1(ModelMap model) {
		      model.addAttribute("message", "Hello Spring MVC Framework!");

		      return "UserManagement";
		   }
}
