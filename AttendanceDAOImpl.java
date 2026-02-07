package com.employeesystem.dao;

import com.employeesystem.config.HibernateConfig;
import com.employeesystem.model.Attendance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Attendance DAO Implementation
 */
public class AttendanceDAOImpl implements AttendanceDAO {
    
    @Override
    public Attendance saveAttendance(Attendance attendance) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(attendance);
            transaction.commit();
            return attendance;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Attendance updateAttendance(Attendance attendance) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(attendance);
            transaction.commit();
            return attendance;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean deleteAttendance(Long attendanceId) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Attendance attendance = session.get(Attendance.class, attendanceId);
            if (attendance != null) {
                session.delete(attendance);
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
    public Attendance getAttendanceById(Long attendanceId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Attendance.class, attendanceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Attendance> getAllAttendance() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Attendance> query = session.createQuery(
                "FROM Attendance ORDER BY attendanceDate DESC", Attendance.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Attendance> query = session.createQuery(
                "FROM Attendance WHERE employee.employeeId = :empId ORDER BY attendanceDate DESC", Attendance.class);
            query.setParameter("empId", employeeId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Attendance> getAttendanceByDate(Date date) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Attendance> query = session.createQuery(
                "FROM Attendance WHERE attendanceDate = :date", Attendance.class);
            query.setParameter("date", date);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Attendance> getAttendanceByEmployeeAndDateRange(Long employeeId, Date startDate, Date endDate) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Attendance> query = session.createQuery(
                "FROM Attendance WHERE employee.employeeId = :empId AND attendanceDate BETWEEN :start AND :end ORDER BY attendanceDate",
                Attendance.class);
            query.setParameter("empId", employeeId);
            query.setParameter("start", startDate);
            query.setParameter("end", endDate);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public long getAttendanceCount() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Attendance", Long.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}