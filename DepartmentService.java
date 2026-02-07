package com.employeesystem.service;

import com.employeesystem.dao.DepartmentDAO;
import com.employeesystem.dao.DepartmentDAOImpl;
import com.employeesystem.model.Department;

import java.util.List;

/**
 * Department Service Class
 */
public class DepartmentService {
    
    private final DepartmentDAO departmentDAO;
    
    public DepartmentService() {
        this.departmentDAO = new DepartmentDAOImpl();
    }
    
    public boolean addDepartment(Department department) {
        try {
            Department saved = departmentDAO.saveDepartment(department);
            if (saved != null) {
                System.out.println("✓ Department added successfully with ID: " + saved.getDepartmentId());
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error adding department: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateDepartment(Department department) {
        try {
            Department updated = departmentDAO.updateDepartment(department);
            if (updated != null) {
                System.out.println("✓ Department updated successfully!");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error updating department: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteDepartment(Long departmentId) {
        try {
            boolean deleted = departmentDAO.deleteDepartment(departmentId);
            if (deleted) {
                System.out.println("✓ Department deleted successfully!");
                return true;
            } else {
                System.out.println("✗ Department not found with ID: " + departmentId);
                return false;
            }
        } catch (Exception e) {
            System.err.println("✗ Error deleting department: " + e.getMessage());
            return false;
        }
    }
    
    public Department getDepartmentById(Long departmentId) {
        return departmentDAO.getDepartmentById(departmentId);
    }
    
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }
    
    public List<Department> getActiveDepartments() {
        return departmentDAO.getActiveDepartments();
    }
    
    public Department getDepartmentByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }
    
    public long getTotalDepartmentCount() {
        return departmentDAO.getDepartmentCount();
    }
    
    public void displayDepartmentList(List<Department> departments) {
        if (departments != null && !departments.isEmpty()) {
            System.out.println("\n" + "=".repeat(100));
            System.out.println("                              DEPARTMENT LIST");
            System.out.println("=".repeat(100));
            System.out.printf("%-5s %-30s %-40s %-20s%n", 
                "ID", "Name", "Description", "Location");
            System.out.println("-".repeat(100));
            
            for (Department dept : departments) {
                System.out.printf("%-5d %-30s %-40s %-20s%n",
                    dept.getDepartmentId(),
                    dept.getDepartmentName(),
                    dept.getDescription() != null ? dept.getDescription() : "N/A",
                    dept.getLocation() != null ? dept.getLocation() : "N/A");
            }
            System.out.println("=".repeat(100));
            System.out.println("Total Departments: " + departments.size());
            System.out.println();
        } else {
            System.out.println("\n✗ No departments found!\n");
        }
    }
}