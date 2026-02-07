package com.employeesystem.dao;

import com.employeesystem.model.Designation;
import java.util.List;

/**
 * Designation DAO Interface
 */
public interface DesignationDAO {
    
    Designation saveDesignation(Designation designation);
    Designation updateDesignation(Designation designation);
    boolean deleteDesignation(Long designationId);
    Designation getDesignationById(Long designationId);
    List<Designation> getAllDesignations();
    List<Designation> getActiveDesignations();
    Designation getDesignationByName(String designationName);
    long getDesignationCount();
}