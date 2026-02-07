package com.employeesystem.config;

import com.employeesystem.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * Hibernate Configuration Class
 * Programmatic Configuration - NO hibernate.cfg.xml needed
 */
public class HibernateConfig {
    
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                
                // Hibernate settings
                Properties settings = new Properties();
                
                // JDBC connection settings
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hib_employeedb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                
                // JDBC connection pool
                settings.put(Environment.POOL_SIZE, "10");
                
                // Hibernate dialect
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                
                // SQL settings
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.USE_SQL_COMMENTS, "true");
                
                // Session context
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                
                // Schema auto update
                settings.put(Environment.HBM2DDL_AUTO, "update");
                
                // C3P0 connection pool
                settings.put(Environment.C3P0_MIN_SIZE, "5");
                settings.put(Environment.C3P0_MAX_SIZE, "20");
                settings.put(Environment.C3P0_TIMEOUT, "300");
                settings.put(Environment.C3P0_MAX_STATEMENTS, "50");
                settings.put(Environment.C3P0_IDLE_TEST_PERIOD, "3000");
                
                configuration.setProperties(settings);
                
                // Add all entity classes
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Department.class);
                configuration.addAnnotatedClass(Designation.class);
                configuration.addAnnotatedClass(Attendance.class);
                configuration.addAnnotatedClass(Salary.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                
                System.out.println("✓ Hibernate SessionFactory created successfully!");
                
            } catch (Exception e) {
                System.err.println("✗ Error creating SessionFactory: " + e.getMessage());
                e.printStackTrace();
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("✓ Hibernate SessionFactory closed!");
        }
    }
}