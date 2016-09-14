package net.codejava.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.Company;
import net.codejava.spring.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService{

	@Resource
    private ShopRepository shopRepository;
 
    @Override
    @Transactional
    public Company create(Company shop) {
        Company createdShop = shop;
        return shopRepository.save(createdShop);
    }
     
    @Override
    @Transactional
    public Company findById(int id) {
        return shopRepository.findOne(id);
    }
 
    @Override
    @Transactional
    public Company delete(int id) {
        Company deletedShop = shopRepository.findOne(id);
         
//        if (deletedShop == null)
//            throw new ShopNotFound();
         
        shopRepository.delete(deletedShop);
        return deletedShop;
    }
 
    @Override
    @Transactional
    public List<Company> findAll() {
        return shopRepository.findAll();
    }
 
    @Override
    @Transactional
    public Company update(Company shop){
        Company updatedShop = shopRepository.findOne(shop.getId());
         
//        if (updatedShop == null)
//            throw new ShopNotFound();
         
        updatedShop.setName(shop.getName());
        updatedShop.setPanNumber(shop.getPanNumber());
        updatedShop.setActive(shop.isActive());
        return shopRepository.save(updatedShop);
//        return updatedShop;
    }

	@Override
	public List<Company> findByName(String name) {
			return shopRepository.findByName(name);
	}

	@Override
	@Transactional
	public List<Company> OrderByPanNumber() {
		return shopRepository.OrderByPanNumber();
	}

	@Override
	public List<Company> OrderByIdDescending() {
		return shopRepository.OrderByIdDescending();
	}

    

}
