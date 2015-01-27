package domain;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class InitSessionFactory {
	
	
	private static final SessionFactory factoriaDatos = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		 try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new AnnotationConfiguration()
	            		.configure()
	                    .buildSessionFactory();
	        } catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	}
	
	public static SessionFactory getSessionFactory() {
        return factoriaDatos;
    }
	
	
	public static void closeSessionFactory() {
		factoriaDatos.close();
	}

}
