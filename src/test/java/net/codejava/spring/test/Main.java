package net.codejava.spring.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import net.codejava.spring.model.Company;

public class Main {
	
	  private static final SessionFactory sessionFactory = buildSessionFactory();

    
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new AnnotationConfiguration().configure()
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void main(String[] args) {
    	 
        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
 
        	Company company=new Company();
        	session.get(Company.class, 1);
        
//         session.update(groupAdmin);
        session.getTransaction().commit();
        session.close();
    }
}