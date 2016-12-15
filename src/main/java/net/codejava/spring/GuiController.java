package net.codejava.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.codejava.spring.dao.GuiFormService;
import net.codejava.spring.model.GuiForm;

@RequestMapping(value = "/guiform")
@Controller
public class GuiController {
	
	@Autowired
	private GuiFormService guiFormService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public List<GuiForm>  listOfCo() {   
		System.out.println("inside list of guis ");
		List<GuiForm> listFormFields = guiFormService.findAll();
		return listFormFields;
	}
	
	
	@RequestMapping(value = "/byformtype/{type}", method = RequestMethod.GET)
	@ResponseBody
	public List<GuiForm>  listOfFieldsByType(@PathVariable("type") String type) {      
		List<GuiForm> listFormFields = guiFormService.findByGuiType(type);
	    return listFormFields;
	}
}
