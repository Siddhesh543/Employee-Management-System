package com.employeesystem.dao;

import com.employeesystem.model.Employee;
import java.util.List;

/**
 * Employee DAO Interface
 */
public interface EmployeeDAO {
    
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(Long employeeId);
    Employee getEmployeeById(Long employeeId);
    List<Employee> getAllEmployees();
    List<Employee> getActiveEmployees();
    List<Employee> getEmployeesByDepartment(Long departmentId);
    List<Employee> getEmployeesByDesignation(Long designationId);
    List<Employee> searchEmployeesByName(String name);
    Employee getEmployeeByEmail(String email);
    long getEmployeeCount();
    boolean emailExists(String email);
}