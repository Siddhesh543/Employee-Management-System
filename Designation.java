package com.employeesystem.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Designation Entity Class
 */
@Entity
@Table(name = "designations")
public class Designation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "designation_id")
    private Long designationId;
    
    @Column(name = "designation_name", nullable = false, unique = true, length = 100)
    private String designationName;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "level", length = 50)
    private String level;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @OneToMany(mappedBy = "designation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
    
    // Constructors
    public Designation() {
        this.isActive = true;
    }
    
    public Designation(String designationName, String description, String level) {
        this.designationName = designationName;
        this.description = description;
        this.level = level;
        this.isActive = true;
    }
    
    // Getters and Setters
    public Long getDesignationId() {
        return designationId;
    }
    
    public void setDesignationId(Long designationId) {
        this.designationId = designationId;
    }
    
    public String getDesignationName() {
        return designationName;
    }
    
    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public Set<Employee> getEmployees() {
        return employees;
    }
    
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    
    @Override
    public String toString() {
        return "Designation{" +
                "designationId=" + designationId +
                ", designationName='" + designationName + '\'' +
                ", description='" + description + '\'' +
                ", level='" + level + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}