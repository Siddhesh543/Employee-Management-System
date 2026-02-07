package com.employeesystem.service;

import com.employeesystem.dao.DesignationDAO;
import com.employeesystem.dao.DesignationDAOImpl;
import com.employeesystem.model.Designation;

import java.util.List;

/**
 * Designation Service Class
 */
public class DesignationService {
    
    private final DesignationDAO designationDAO;
    
    public DesignationService() {
        this.designationDAO = new DesignationDAOImpl();
    }
    
    public boolean addDesignation(Designation designation) {
        try {
            Designation saved = designationDAO.saveDesignation(designation);
            if (saved != null) {
                System.out.println("✓ Designation added successfully with ID: " + saved.getDesignationId());
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error adding designation: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateDesignation(Designation designation) {
        try {
            Designation updated = designationDAO.updateDesignation(designation);
            if (updated != null) {
                System.out.println("✓ Designation updated successfully!");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("✗ Error updating designation: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteDesignation(Long designationId) {
        try {
            boolean deleted = designationDAO.deleteDesignation(designationId);
            if (deleted) {
                System.out.println("✓ Designation deleted successfully!");
                return true;
            } else {
                System.out.println("✗ Designation not found with ID: " + designationId);
                return false;
            }
        } catch (Exception e) {
            System.err.println("✗ Error deleting designation: " + e.getMessage());
            return false;
        }
    }
    
    public Designation getDesignationById(Long designationId) {
        return designationDAO.getDesignationById(designationId);
    }
    
    public List<Designation> getAllDesignations() {
        return designationDAO.getAllDesignations();
    }
    
    public List<Designation> getActiveDesignations() {
        return designationDAO.getActiveDesignations();
    }
    
    public Designation getDesignationByName(String name) {
        return designationDAO.getDesignationByName(name);
    }
    
    public long getTotalDesignationCount() {
        return designationDAO.getDesignationCount();
    }
    
    public void displayDesignationList(List<Designation> designations) {
        if (designations != null && !designations.isEmpty()) {
            System.out.println("\n" + "=".repeat(100));
            System.out.println("                              DESIGNATION LIST");
            System.out.println("=".repeat(100));
            System.out.printf("%-5s %-30s %-40s %-20s%n", 
                "ID", "Name", "Description", "Level");
            System.out.println("-".repeat(100));
            
            for (Designation des : designations) {
                System.out.printf("%-5d %-30s %-40s %-20s%n",
                    des.getDesignationId(),
                    des.getDesignationName(),
                    des.getDescription() != null ? des.getDescription() : "N/A",
                    des.getLevel() != null ? des.getLevel() : "N/A");
            }
            System.out.println("=".repeat(100));
            System.out.println("Total Designations: " + designations.size());
            System.out.println();
        } else {
            System.out.println("\n✗ No designations found!\n");
        }
    }
}