package com.employeesystem.dao;

import com.employeesystem.config.HibernateConfig;
import com.employeesystem.model.Designation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Designation DAO Implementation
 */
public class DesignationDAOImpl implements DesignationDAO {
    
    @Override
    public Designation saveDesignation(Designation designation) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(designation);
            transaction.commit();
            return designation;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Designation updateDesignation(Designation designation) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(designation);
            transaction.commit();
            return designation;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean deleteDesignation(Long designationId) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Designation designation = session.get(Designation.class, designationId);
            if (designation != null) {
                session.delete(designation);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Designation getDesignationById(Long designationId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Designation.class, designationId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Designation> getAllDesignations() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Designation> query = session.createQuery("FROM Designation ORDER BY designationName", Designation.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Designation> getActiveDesignations() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Designation> query = session.createQuery(
                "FROM Designation WHERE isActive = true ORDER BY designationName", Designation.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Designation getDesignationByName(String designationName) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Designation> query = session.createQuery(
                "FROM Designation WHERE designationName = :name", Designation.class);
            query.setParameter("name", designationName);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public long getDesignationCount() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Designation", Long.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}