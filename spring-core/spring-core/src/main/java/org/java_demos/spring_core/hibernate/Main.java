package org.java_demos.spring_core.hibernate;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        BootstrapServiceRegistry bootstrapServiceRegistry = new BootstrapServiceRegistryBuilder().build();

        Properties properties = new Properties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("hibernate.properties"));

        Configuration configuration = new Configuration(bootstrapServiceRegistry).setProperties(properties);
        configuration.addAnnotatedClass(Message.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        System.out.println("================" + configuration.getProperties());

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder(bootstrapServiceRegistry)
                .applySettings(configuration.getProperties())
                .build();

        SessionFactory factory = configuration.buildSessionFactory(ssr);

        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();

            Message message = new Message(new Date(), "messagen");

            session.persist(message);
            tx.commit();
            System.out.println("successfully saved");

            session.detach(message);
            System.out.println("===============" + message + session);
            session.merge(message);
            System.out.println("===============" + message + session);

            // hql
            String hql = "FROM Message";
            List<Message> list = session.createQuery(hql, Message.class).list();
            System.out.println("===============" + message + session);
            System.out.println(list);

            // criteria api
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
            JpaRoot<Message> root = query.from(Message.class);
            JpaCriteriaQuery<Message> select = query.select(root);
            query.where(criteriaBuilder.lessThanOrEqualTo(root.get("id"), 4));

            list = session.createQuery(select).list();
            System.out.println("===============" + message + session);
            System.out.println(list);

            Query query1 = session.createNativeQuery("SELECT * FROM MESSAGE", Message.class);
            List<Message> resultList = query1.getResultList();
            System.out.println("===============" + message + session);
            System.out.println(resultList);

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        }
        factory.close();
    }
}
