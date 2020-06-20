package util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	

    private static SessionFactory sessionAnnotationFactory;

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            
            //add your entities here
            configuration.addAnnotatedClass(entities.Student.class);
            configuration.addAnnotatedClass(entities.Seance.class);
            configuration.addAnnotatedClass(entities.Module.class);
            configuration.addAnnotatedClass(entities.Groupe.class);
            configuration.addAnnotatedClass(entities.Evaluation.class);
            configuration.addAnnotatedClass(entities.Enseignant.class);
            configuration.addAnnotatedClass(entities.Emploi.class);
            configuration.addAnnotatedClass(entities.Absence.class);
            
            //finish configuration
            System.out.println("Hibernate Annotation Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static SessionFactory getSessionFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

}
