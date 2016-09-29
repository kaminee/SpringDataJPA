package net.codejava.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	
	@RequestMapping(value="/")
	public ModelAndView home() {
//		List<User> listUsers = userDao.list();
		System.out.println("\n\t\t ...loading home page....");
		ModelAndView model = new ModelAndView("home");
//		model.addObject("userList", listUsers);
		return model;
	}
	
	@RequestMapping(value = "/customlogin", method = RequestMethod.GET)
	public ModelAndView login(ModelMap mode,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		System.out.println("\n\t -------------");
		ModelAndView model = new ModelAndView("login");
		
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		return model;

	}



}
