package com.employeesystem.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Attendance Entity Class
 */
@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "attendance_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date attendanceDate;
    
    @Column(name = "check_in_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInTime;
    
    @Column(name = "check_out_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutTime;
    
    @Column(name = "status", length = 20)
    private String status;
    
    @Column(name = "remarks", length = 500)
    private String remarks;
    
    // Constructors
    public Attendance() {
    }
    
    public Attendance(Employee employee, Date attendanceDate, Date checkInTime, 
                     Date checkOutTime, String status) {
        this.employee = employee;
        this.attendanceDate = attendanceDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getAttendanceId() {
        return attendanceId;
    }
    
    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Date getAttendanceDate() {
        return attendanceDate;
    }
    
    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
    
    public Date getCheckInTime() {
        return checkInTime;
    }
    
    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }
    
    public Date getCheckOutTime() {
        return checkOutTime;
    }
    
    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", employee=" + (employee != null ? employee.getFullName() : "N/A") +
                ", attendanceDate=" + attendanceDate +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", status='" + status + '\'' +
                '}';
    }
}