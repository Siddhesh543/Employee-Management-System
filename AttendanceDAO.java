package com.employeesystem.dao;

import com.employeesystem.model.Attendance;
import java.util.Date;
import java.util.List;

/**
 * Attendance DAO Interface
 */
public interface AttendanceDAO {
    
    Attendance saveAttendance(Attendance attendance);
    Attendance updateAttendance(Attendance attendance);
    boolean deleteAttendance(Long attendanceId);
    Attendance getAttendanceById(Long attendanceId);
    List<Attendance> getAllAttendance();
    List<Attendance> getAttendanceByEmployee(Long employeeId);
    List<Attendance> getAttendanceByDate(Date date);
    List<Attendance> getAttendanceByEmployeeAndDateRange(Long employeeId, Date startDate, Date endDate);
    long getAttendanceCount();
}