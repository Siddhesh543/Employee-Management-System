package com.employeesystem.dao;

import com.employeesystem.config.HibernateConfig;
import com.employeesystem.model.Salary;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Salary DAO Implementation
 */
public class SalaryDAOImpl implements SalaryDAO {
    
    @Override
    public Salary saveSalary(Salary salary) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(salary);
            transaction.commit();
            return salary;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Salary updateSalary(Salary salary) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(salary);
            transaction.commit();
            return salary;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean deleteSalary(Long salaryId) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Salary salary = session.get(Salary.class, salaryId);
            if (salary != null) {
                session.delete(salary);
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
    public Salary getSalaryById(Long salaryId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Salary.class, salaryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Salary> getAllSalaries() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Salary> query = session.createQuery(
                "FROM Salary ORDER BY salaryMonth DESC", Salary.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Salary> getSalariesByEmployee(Long employeeId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Salary> query = session.createQuery(
                "FROM Salary WHERE employee.employeeId = :empId ORDER BY salaryMonth DESC", Salary.class);
            query.setParameter("empId", employeeId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Salary> getSalariesByMonth(Date month) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Salary> query = session.createQuery(
                "FROM Salary WHERE salaryMonth = :month", Salary.class);
            query.setParameter("month", month);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Salary> getPendingSalaries() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Salary> query = session.createQuery(
                "FROM Salary WHERE paymentStatus = 'Pending' ORDER BY salaryMonth", Salary.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Salary> getPaidSalaries() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Salary> query = session.createQuery(
                "FROM Salary WHERE paymentStatus = 'Paid' ORDER BY salaryMonth DESC", Salary.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public long getSalaryCount() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Salary", Long.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}