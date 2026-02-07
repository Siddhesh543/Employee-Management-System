package com.employeesystem.dao;

import com.employeesystem.config.HibernateConfig;
import com.employeesystem.model.Department;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Department DAO Implementation
 */
public class DepartmentDAOImpl implements DepartmentDAO {
    
    @Override
    public Department saveDepartment(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
            return department;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Department updateDepartment(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
            return department;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean deleteDepartment(Long departmentId) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departmentId);
            if (department != null) {
                session.delete(department);
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
    public Department getDepartmentById(Long departmentId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Department.class, departmentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Department> getAllDepartments() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Department> query = session.createQuery("FROM Department ORDER BY departmentName", Department.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Department> getActiveDepartments() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Department> query = session.createQuery(
                "FROM Department WHERE isActive = true ORDER BY departmentName", Department.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Department getDepartmentByName(String departmentName) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Department> query = session.createQuery(
                "FROM Department WHERE departmentName = :name", Department.class);
            query.setParameter("name", departmentName);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public long getDepartmentCount() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Department", Long.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}