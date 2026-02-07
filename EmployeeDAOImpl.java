package com.employeesystem.dao;

import com.employeesystem.config.HibernateConfig;
import com.employeesystem.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Employee DAO Implementation
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    
    @Override
    public Employee saveEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Employee updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean deleteEmployee(Long employeeId) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
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
    public Employee getEmployeeById(Long employeeId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Employee.class, employeeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee ORDER BY employeeId", Employee.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Employee> getActiveEmployees() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery(
                "FROM Employee WHERE isActive = true ORDER BY employeeId", Employee.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery(
                "FROM Employee WHERE department.departmentId = :deptId ORDER BY lastName, firstName", Employee.class);
            query.setParameter("deptId", departmentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Employee> getEmployeesByDesignation(Long designationId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery(
                "FROM Employee WHERE designation.designationId = :desigId ORDER BY lastName, firstName", Employee.class);
            query.setParameter("desigId", designationId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Employee> searchEmployeesByName(String name) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery(
                "FROM Employee WHERE LOWER(firstName) LIKE LOWER(:name) OR LOWER(lastName) LIKE LOWER(:name) ORDER BY lastName, firstName",
                Employee.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Employee getEmployeeByEmail(String email) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery(
                "FROM Employee WHERE email = :email", Employee.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public long getEmployeeCount() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Employee", Long.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public boolean emailExists(String email) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                "SELECT COUNT(*) FROM Employee WHERE email = :email", Long.class);
            query.setParameter("email", email);
            return query.uniqueResult() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}