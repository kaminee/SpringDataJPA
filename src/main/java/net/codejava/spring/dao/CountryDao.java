package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Country;

public interface CountryDao {

	List<Country> fetchAllCountries(); 
	
}
