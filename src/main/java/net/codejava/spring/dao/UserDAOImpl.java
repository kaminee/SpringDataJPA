package net.codejava.spring.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.User;

//@Transactional 
@Component
public class UserDAOImpl implements UserDAO {
	private static final AtomicInteger counter = new AtomicInteger();
//	private EntityManagerFactory
	private SessionFactory sessionFactory;
	public UserDAOImpl(){
		
	}
	private static List<User> users;
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<User> list() {
		System.out.println("\n\t sessionFactory================="+sessionFactory);
		@SuppressWarnings("unchecked")
		List<User> listUser = sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}

	@Override
	public User findById(long id) {
		
		List<User> users = sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		System.out.println("\n\n\t ----users-------->"+users.size());
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	@Override
	public User findByName(String name) {
		
		List<User> users = sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
//		user.setId(5);
		 Session sessionOne = sessionFactory.openSession();
	      sessionOne.beginTransaction();
	      System.out.println("\n\n\t saving user --->"+user.getEmail()+"\t --->");
	      sessionOne.save(user);
	      sessionOne.getTransaction().commit();
	      
	}

	@Override
	@Transactional
	public void updateUser(User user) {
//		int index = users.indexOf(user);
//		users.set(index, user);
//		
		 Session sessionOne = sessionFactory.openSession();
	      sessionOne.beginTransaction();
	      sessionOne.update(user);
	      sessionOne.getTransaction().commit();
		
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

        int userid = 2;

        try {
            session.beginTransaction();

            User user = (User) session.get(User.class, userid);

            session.delete(user);

            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
