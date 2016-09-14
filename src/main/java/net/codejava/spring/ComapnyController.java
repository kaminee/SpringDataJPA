package net.codejava.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import net.codejava.spring.dao.ShopService;
import net.codejava.spring.model.Company;

@Controller
@RequestMapping(value = "/company")
public class ComapnyController {
	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newShopPage() {
		ModelAndView mav = new ModelAndView("shop-new", "shop", new Company());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewShop(@RequestBody Company shop, UriComponentsBuilder ucBuilder, final RedirectAttributes redirectAttributes) {

		String message = "New shop " + shop.getName() + " was successfully created.";
		System.out.println(message);
		shopService.create(shop);
		
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexPage(@ModelAttribute Company shop, final RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
/*	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView shopListPage() {
		ModelAndView mav = new ModelAndView("shop-list");
		List<Company> shopList = shopService.findAll();
		List<Company> shopListByname = shopService.OrderByIdDescending();
		
		for (Company shop : shopListByname) {
			System.out.println("\n\n\t ========>"+shop.getName()+"\t ="+shop.getPanNumber());
		}
		System.out.println("\n\n\t =shopList======>"+shopList.size()+"\t shopListByname=="+shopListByname.size());
		mav.addObject("shopList", shopList);
		return mav;
	}
	*/
	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	    public ResponseEntity<List<Company>> listAllUsers() {
		 List<Company> shopList = shopService.findAll();
//			List<Company> shopListByname = shopService.OrderByIdDescending();
	        return new ResponseEntity<List<Company>>(shopList, HttpStatus.OK);
	    }

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editShopPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("shop-edit");
		Company shop = shopService.findById(id); 
		mav.addObject("shop", shop);
		return mav;
	}
    //------------------- Update a Shop --------------------------------------------------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Company> editShop(@RequestBody Company shop, @PathVariable("id") Integer id,
			final RedirectAttributes redirectAttributes) // throws ShopNotFound
	{
		String message = "Shop was successfully updated.";
		System.out.println(message);

		shopService.update(shop);

//		redirectAttributes.addFlashAttribute("message", message);
        return new ResponseEntity<Company>(shop, HttpStatus.OK);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteShop(@PathVariable Integer id, final RedirectAttributes redirectAttributes)// throws
																											// ShopNotFound
	{

		ModelAndView mav = new ModelAndView("redirect:/shop/list");

		Company shop = shopService.delete(id);
		String message = "The shop " + shop.getName() + " was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchShopPage() {
		ModelAndView mav = new ModelAndView("shop-search");
		List<Company> shopList = shopService.findAll();
		mav.addObject("shopList", shopList);
		return mav;
	}
	
	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
	public ModelAndView searchPOSTShopPage(@PathVariable String name) {
		ModelAndView mav = new ModelAndView("shop-search");
		List<Company> shopListByname = shopService.findByName(name);
		mav.addObject("shopList", shopListByname);
		return mav;
	}
	
	
}
