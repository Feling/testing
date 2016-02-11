package main.java.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
		 
	   /* private static final SessionFactory sessionFactory = buildSessionFactory();
	 
	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure().buildSessionFactory();
	        } catch (Throwable ex) {
	            System.err.println("SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	 
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	}*/

	private static SessionFactory sessionFactory;
	
	private HibernateUtil() {}
	
	static{
		try{
		sessionFactory = new Configuration().configure().buildSessionFactory();
				//new AnnotationConfiguration().configure().buildSessionFactory();
				//new AnnotationConfiguration().configure().buildSessionFactory();
		//new Configuration().configure().buildSessionFactory();
	}catch(Throwable e){
		throw new ExceptionInInitializerError(e);
	}
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}

