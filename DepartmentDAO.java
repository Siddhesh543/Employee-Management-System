package com.employeesystem.dao;

import com.employeesystem.model.Department;
import java.util.List;

/**
 * Department DAO Interface
 */
public interface DepartmentDAO {
    
    Department saveDepartment(Department department);
    Department updateDepartment(Department department);
    boolean deleteDepartment(Long departmentId);
    Department getDepartmentById(Long departmentId);
    List<Department> getAllDepartments();
    List<Department> getActiveDepartments();
    Department getDepartmentByName(String departmentName);
    long getDepartmentCount();
}