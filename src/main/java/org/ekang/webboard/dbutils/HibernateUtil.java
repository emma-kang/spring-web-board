package org.ekang.webboard.dbutils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    static SessionFactory sessionFactory;
    static StandardServiceRegistry serviceRegistry;
    private static String configFile = "hibernate.cfg.xml";

    static {
        try {
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure().build(); // configures settings from hibernate.cfg.xml
            sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
