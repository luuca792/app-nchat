package controllers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnect {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // EntityManagerFactory ENTITY = Persistence.createEntityManagerFactory("DemoHibernate");
        // EntityManager entityManager = ENTITY.createEntityManager();
        // Session session = entityManager.unwrap(org.hibernate.Session.class);
        // SessionFactory factory = session.getSessionFactory();
        // return factory;
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
