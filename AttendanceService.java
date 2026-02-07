package com.employeesystem.service;

import com.employeesystem.dao.AttendanceDAO;
import com.employeesystem.dao.AttendanceDAOImpl;
import com.employeesystem.model.Attendance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Attendance Service Class
 */
public class AttendanceService {
    
    private final AttendanceDAO attendanceDAO;
    private final SimpleDateFormat dateFormat;
    private final SimpleDateFormat timeFormat;
    
    public AttendanceService() {
        this.attendanceDAO = new AttendanceDAOImpl();
        this.dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        this.timeFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    }
    
    public boolean markAttendance(Attendance attendance) {
        try {
            Attendance saved = attendanceDAO.saveAttendance(attendance);
            if (saved != null) {
                System.out.println("✓ Attendance marked successfully with ID: " + saved.getAttendanceId());
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error marking attendance: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateAttendance(Attendance attendance) {
        try {
            Attendance updated = attendanceDAO.updateAttendance(attendance);
            if (updated != null) {
                System.out.println("✓ Attendance updated successfully!");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error updating attendance: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteAttendance(Long attendanceId) {
        try {
            boolean deleted = attendanceDAO.deleteAttendance(attendanceId);
            if (deleted) {
                System.out.println("✓ Attendance deleted successfully!");
                return true;
            } else {
                System.out.println("✗ Attendance not found with ID: " + attendanceId);
                return false;
            }
        } catch (Exception e) {
            System.err.println("✗ Error deleting attendance: " + e.getMessage());
            return false;
        }
    }
    
    public Attendance getAttendanceById(Long attendanceId) {
        return attendanceDAO.getAttendanceById(attendanceId);
    }
    
    public List<Attendance> getAllAttendance() {
        return attendanceDAO.getAllAttendance();
    }
    
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return attendanceDAO.getAttendanceByEmployee(employeeId);
    }
    
    public List<Attendance> getAttendanceByDate(Date date) {
        return attendanceDAO.getAttendanceByDate(date);
    }
    
    public List<Attendance> getAttendanceByEmployeeAndDateRange(Long employeeId, Date startDate, Date endDate) {
        return attendanceDAO.getAttendanceByEmployeeAndDateRange(employeeId, startDate, endDate);
    }
    
    public long getTotalAttendanceCount() {
        return attendanceDAO.getAttendanceCount();
    }
    
    public void displayAttendanceList(List<Attendance> attendanceList) {
        if (attendanceList != null && !attendanceList.isEmpty()) {
            System.out.println("\n" + "=".repeat(120));
            System.out.println("                                    ATTENDANCE RECORDS");
            System.out.println("=".repeat(120));
            System.out.printf("%-5s %-25s %-15s %-20s %-20s %-15s%n", 
                "ID", "Employee", "Date", "Check-In", "Check-Out", "Status");
            System.out.println("-".repeat(120));
            
            for (Attendance att : attendanceList) {
                System.out.printf("%-5d %-25s %-15s %-20s %-20s %-15s%n",
                    att.getAttendanceId(),
                    att.getEmployee() != null ? att.getEmployee().getFullName() : "N/A",
                    dateFormat.format(att.getAttendanceDate()),
                    att.getCheckInTime() != null ? timeFormat.format(att.getCheckInTime()) : "N/A",
                    att.getCheckOutTime() != null ? timeFormat.format(att.getCheckOutTime()) : "N/A",
                    att.getStatus());
            }
            System.out.println("=".repeat(120));
            System.out.println("Total Records: " + attendanceList.size());
            System.out.println();
        } else {
            System.out.println("\n✗ No attendance records found!\n");
        }
    }
}