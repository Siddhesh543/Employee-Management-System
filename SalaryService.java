package com.employeesystem.service;

import com.employeesystem.dao.SalaryDAO;
import com.employeesystem.dao.SalaryDAOImpl;
import com.employeesystem.model.Salary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Salary Service Class
 */
public class SalaryService {
    
    private final SalaryDAO salaryDAO;
    private final SimpleDateFormat dateFormat;
    
    public SalaryService() {
        this.salaryDAO = new SalaryDAOImpl();
        this.dateFormat = new SimpleDateFormat("MMM-yyyy");
    }
    
    public boolean processSalary(Salary salary) {
        try {
            Salary saved = salaryDAO.saveSalary(salary);
            if (saved != null) {
                System.out.println("✓ Salary processed successfully with ID: " + saved.getSalaryId());
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error processing salary: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateSalary(Salary salary) {
        try {
            Salary updated = salaryDAO.updateSalary(salary);
            if (updated != null) {
                System.out.println("✓ Salary updated successfully!");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error updating salary: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteSalary(Long salaryId) {
        try {
            boolean deleted = salaryDAO.deleteSalary(salaryId);
            if (deleted) {
                System.out.println("✓ Salary record deleted successfully!");
                return true;
            } else {
                System.out.println("✗ Salary record not found with ID: " + salaryId);
                return false;
            }
        } catch (Exception e) {
            System.err.println("✗ Error deleting salary: " + e.getMessage());
            return false;
        }
    }
    
    public Salary getSalaryById(Long salaryId) {
        return salaryDAO.getSalaryById(salaryId);
    }
    
    public List<Salary> getAllSalaries() {
        return salaryDAO.getAllSalaries();
    }
    
    public List<Salary> getSalariesByEmployee(Long employeeId) {
        return salaryDAO.getSalariesByEmployee(employeeId);
    }
    
    public List<Salary> getSalariesByMonth(Date month) {
        return salaryDAO.getSalariesByMonth(month);
    }
    
    public List<Salary> getPendingSalaries() {
        return salaryDAO.getPendingSalaries();
    }
    
    public List<Salary> getPaidSalaries() {
        return salaryDAO.getPaidSalaries();
    }
    
    public long getTotalSalaryCount() {
        return salaryDAO.getSalaryCount();
    }
    
    public void displaySalaryList(List<Salary> salaries) {
        if (salaries != null && !salaries.isEmpty()) {
            System.out.println("\n" + "=".repeat(130));
            System.out.println("                                      SALARY RECORDS");
            System.out.println("=".repeat(130));
            System.out.printf("%-5s %-25s %-12s %-12s %-12s %-12s %-12s %-15s%n", 
                "ID", "Employee", "Month", "Basic", "Allowances", "Deductions", "Net Salary", "Status");
            System.out.println("-".repeat(130));
            
            for (Salary sal : salaries) {
                System.out.printf("%-5d %-25s %-12s $%-11.2f $%-11.2f $%-11.2f $%-11.2f %-15s%n",
                    sal.getSalaryId(),
                    sal.getEmployee() != null ? sal.getEmployee().getFullName() : "N/A",
                    dateFormat.format(sal.getSalaryMonth()),
                    sal.getBasicSalary(),
                    sal.getAllowances() != null ? sal.getAllowances() : 0.0,
                    sal.getDeductions() != null ? sal.getDeductions() : 0.0,
                    sal.getNetSalary(),
                    sal.getPaymentStatus());
            }
            System.out.println("=".repeat(130));
            System.out.println("Total Records: " + salaries.size());
            System.out.println();
        } else {
            System.out.println("\n✗ No salary records found!\n");
        }
    }
}