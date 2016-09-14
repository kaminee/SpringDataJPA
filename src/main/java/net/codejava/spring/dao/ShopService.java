package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Company;

public interface ShopService {
	public Company create(Company shop);
    public Company delete(int id);
    public List<Company> findAll();
    public Company update(Company shop);
    public Company findById(int id);
    public List<Company> findByName(String name);
    public List<Company> OrderByPanNumber();
	List<Company> OrderByIdDescending();


}
