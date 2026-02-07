package com.employeesystem.dao;

import com.employeesystem.model.Salary;
import java.util.Date;
import java.util.List;

/**
 * Salary DAO Interface
 */
public interface SalaryDAO {
    
    Salary saveSalary(Salary salary);
    Salary updateSalary(Salary salary);
    boolean deleteSalary(Long salaryId);
    Salary getSalaryById(Long salaryId);
    List<Salary> getAllSalaries();
    List<Salary> getSalariesByEmployee(Long employeeId);
    List<Salary> getSalariesByMonth(Date month);
    List<Salary> getPendingSalaries();
    List<Salary> getPaidSalaries();
    long getSalaryCount();
}