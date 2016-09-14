package net.codejava.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.Employee;
import net.codejava.spring.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Resource
    private EmployeeRepository employeeRepository;
 
    @Override
    @Transactional
    public Employee create(Employee shop) {
    	Employee createdShop = shop;
        return employeeRepository.save(createdShop);
    }
     
   
 
    @Override
    @Transactional
    public Employee delete(int id) {
    	Employee deletedShop = employeeRepository.findOne(id);
         
//        if (deletedShop == null)
//            throw new ShopNotFound();
         
    	employeeRepository.delete(deletedShop);
        return deletedShop;
    }
 
    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
 
    @Override
    @Transactional
    public Employee update(Employee shop){
    	/*Employee updatedShop = employeeRepository.findOne(shop.getEmployeeId());
         
//        if (updatedShop == null)
//            throw new ShopNotFound();
         
        updatedShop.setName(shop.getName());
        updatedShop.setPanNumber(shop.getPanNumber());
        updatedShop.setActive(shop.isActive());*/
        return employeeRepository.save(shop);
//        return updatedShop;
    }



	@Override
	public List<Employee> findByFirstname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	

    

}
