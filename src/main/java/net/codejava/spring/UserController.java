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

import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.User;

/**
 * Handles requests for the application home page.
 */
@RequestMapping(value = "/user")
@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<User>  listOfUsers() {      
		List<User> listUsers = userDao.list();
	    return listUsers;
	}
	
	 @RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");

	      return "UserManagement";
	   }
	 
	  @RequestMapping(value = "", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating User " + user.getUsername());
	 
	        userDao.saveUser(user);
	 
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	  
	  
	   @RequestMapping(value = "/user/", method = RequestMethod.GET)
	    public ResponseEntity<List<User>> listAllUsers() {
	        List<User> users = userDao.list();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	    }
	   
	   
	    //------------------- Update a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
	        System.out.println("Updating User " + id);
	         
	        User currentUser = userDao.findById(id);
	        System.out.println("currentUser " + currentUser+"\t -->"+user.getUsername());
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentUser.setUsername(user.getUsername());
	        currentUser.setAddress(user.getAddress());
	        currentUser.setEmail(user.getEmail());
	         
	        userDao.updateUser(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }
	    
	    
		 @RequestMapping(value = "/loadUsers", method = RequestMethod.GET)
		   public String printHello1(ModelMap model) {
		      model.addAttribute("message", "Hello Spring MVC Framework!");

		      return "UserManagement";
		   }
}
