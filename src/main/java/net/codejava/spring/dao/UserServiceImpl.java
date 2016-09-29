package net.codejava.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.Role;
import net.codejava.spring.model.User;
import net.codejava.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Resource
    private UserRepository userRepository;
 
    @Override
    @Transactional
    public User create(User shop) {
    	User createdShop = shop;
        return userRepository.save(createdShop);
    }
     
    @Override
    @Transactional
    public User findById(int id) {
        return userRepository.findOne(id);
    }
 
    @Override
    @Transactional
    public User delete(int id) {
    	User deletedShop = userRepository.findOne(id);
         
//        if (deletedShop == null)
//            throw new ShopNotFound();
         
    	userRepository.delete(deletedShop);
        return deletedShop;
    }
 
    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
 
    @Override
    @Transactional
    public User update(User shop){
    	User updatedShop = userRepository.findOne(shop.getId());
         
//        if (updatedShop == null)
//            throw new ShopNotFound();
         
      
        return userRepository.save(updatedShop);
//        return updatedShop;
    }

	@Override
	public User findByUsername(String name) {
			return userRepository.findByUsername(name);
	}

	@Override
	public List<Role> findRolesByUsername(String name) {
		return userRepository.findRolesByUsername(name);
	}


}
