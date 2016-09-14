package net.codejava.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.Country;

@Transactional 
@Component
public class CountryDaoImpl implements CountryDao {

	private SessionFactory sessionFactory;
	
	public CountryDaoImpl(){
		
	}
	public CountryDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public List<Country> fetchAllCountries() {
		
		System.out.println("\n\n\t =====sessionFactory==>"+sessionFactory);
		List<Country> listCountries= sessionFactory.getCurrentSession()
				.createCriteria(Country.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();

		return listCountries;
	}
	
}
