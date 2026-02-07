package com.employeesystem.service;

import com.employeesystem.dao.EmployeeDAO;
import com.employeesystem.dao.EmployeeDAOImpl;
import com.employeesystem.model.Employee;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Employee Service Class
 */
public class EmployeeService {
    
    private final EmployeeDAO employeeDAO;
    private final SimpleDateFormat dateFormat;
    
    public EmployeeService() {
        this.employeeDAO = new EmployeeDAOImpl();
        this.dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    }
    
    public boolean addEmployee(Employee employee) {
        try {
            if (employeeDAO.emailExists(employee.getEmail())) {
                System.out.println("✗ Email already exists: " + employee.getEmail());
                return false;
            }
            
            Employee saved = employeeDAO.saveEmployee(employee);
            if (saved != null) {
                System.out.println("✓ Employee added successfully with ID: " + saved.getEmployeeId());
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error adding employee: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateEmployee(Employee employee) {
        try {
            Employee updated = employeeDAO.updateEmployee(employee);
            if (updated != null) {
                System.out.println("✓ Employee updated successfully!");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error updating employee: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteEmployee(Long employeeId) {
        try {
            boolean deleted = employeeDAO.deleteEmployee(employeeId);
            if (deleted) {
                System.out.println("✓ Employee deleted successfully!");
                return true;
            } else {
                System.out.println("✗ Employee not found with ID: " + employeeId);
                return false;
            }
        } catch (Exception e) {
            System.err.println("✗ Error deleting employee: " + e.getMessage());
            return false;
        }
    }
    
    public Employee getEmployeeById(Long employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    
    public List<Employee> getActiveEmployees() {
        return employeeDAO.getActiveEmployees();
    }
    
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeDAO.getEmployeesByDepartment(departmentId);
    }
    
    public List<Employee> getEmployeesByDesignation(Long designationId) {
        return employeeDAO.getEmployeesByDesignation(designationId);
    }
    
    public List<Employee> searchEmployeesByName(String name) {
        return employeeDAO.searchEmployeesByName(name);
    }
    
    public Employee getEmployeeByEmail(String email) {
        return employeeDAO.getEmployeeByEmail(email);
    }
    
    public long getTotalEmployeeCount() {
        return employeeDAO.getEmployeeCount();
    }
    
    public void displayEmployee(Employee employee) {
        if (employee != null) {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("                          EMPLOYEE DETAILS");
            System.out.println("=".repeat(80));
            System.out.printf("%-25s: %d%n", "Employee ID", employee.getEmployeeId());
            System.out.printf("%-25s: %s%n", "Name", employee.getFullName());
            System.out.printf("%-25s: %s%n", "Email", employee.getEmail());
            System.out.printf("%-25s: %s%n", "Phone", employee.getPhone());
            System.out.printf("%-25s: %s%n", "Department", 
                employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : "N/A");
            System.out.printf("%-25s: %s%n", "Designation", 
                employee.getDesignation() != null ? employee.getDesignation().getDesignationName() : "N/A");
            System.out.printf("%-25s: $%.2f%n", "Salary", employee.getSalary());
            System.out.printf("%-25s: %s%n", "Hire Date", dateFormat.format(employee.getHireDate()));
            System.out.printf("%-25s: %s%n", "Status", employee.getIsActive() ? "Active" : "Inactive");
            System.out.println("=".repeat(80) + "\n");
        } else {
            System.out.println("✗ Employee not found!");
        }
    }
    
    public void displayEmployeeList(List<Employee> employees) {
        if (employees != null && !employees.isEmpty()) {
            System.out.println("\n" + "=".repeat(130));
            System.out.println("                                       EMPLOYEE LIST");
            System.out.println("=".repeat(130));
            System.out.printf("%-5s %-25s %-30s %-20s %-20s %-15s%n", 
                "ID", "Name", "Email", "Department", "Designation", "Salary");
            System.out.println("-".repeat(130));
            
            for (Employee emp : employees) {
                System.out.printf("%-5d %-25s %-30s %-20s %-20s $%-14.2f%n",
                    emp.getEmployeeId(),
                    emp.getFullName(),
                    emp.getEmail(),
                    emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "N/A",
                    emp.getDesignation() != null ? emp.getDesignation().getDesignationName() : "N/A",
                    emp.getSalary());
            }
            System.out.println("=".repeat(130));
            System.out.println("Total Employees: " + employees.size());
            System.out.println();
        } else {
            System.out.println("\n✗ No employees found!\n");
        }
    }
}