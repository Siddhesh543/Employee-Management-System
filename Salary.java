package com.employeesystem.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Salary Entity Class
 */
@Entity
@Table(name = "salaries")
public class Salary implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private Long salaryId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "salary_month", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date salaryMonth;
    
    @Column(name = "basic_salary", nullable = false)
    private Double basicSalary;
    
    @Column(name = "allowances")
    private Double allowances;
    
    @Column(name = "deductions")
    private Double deductions;
    
    @Column(name = "bonus")
    private Double bonus;
    
    @Column(name = "net_salary", nullable = false)
    private Double netSalary;
    
    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    
    @Column(name = "payment_status", length = 20)
    private String paymentStatus;
    
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;
    
    @Column(name = "remarks", length = 500)
    private String remarks;
    
    // Constructors
    public Salary() {
        this.paymentStatus = "Pending";
    }
    
    public Salary(Employee employee, Date salaryMonth, Double basicSalary, 
                 Double allowances, Double deductions, Double bonus) {
        this.employee = employee;
        this.salaryMonth = salaryMonth;
        this.basicSalary = basicSalary;
        this.allowances = allowances != null ? allowances : 0.0;
        this.deductions = deductions != null ? deductions : 0.0;
        this.bonus = bonus != null ? bonus : 0.0;
        this.netSalary = calculateNetSalary();
        this.paymentStatus = "Pending";
    }
    
    // Helper method
    public Double calculateNetSalary() {
        double total = basicSalary;
        if (allowances != null) total += allowances;
        if (bonus != null) total += bonus;
        if (deductions != null) total -= deductions;
        return total;
    }
    
    // Getters and Setters
    public Long getSalaryId() {
        return salaryId;
    }
    
    public void setSalaryId(Long salaryId) {
        this.salaryId = salaryId;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Date getSalaryMonth() {
        return salaryMonth;
    }
    
    public void setSalaryMonth(Date salaryMonth) {
        this.salaryMonth = salaryMonth;
    }
    
    public Double getBasicSalary() {
        return basicSalary;
    }
    
    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
        this.netSalary = calculateNetSalary();
    }
    
    public Double getAllowances() {
        return allowances;
    }
    
    public void setAllowances(Double allowances) {
        this.allowances = allowances;
        this.netSalary = calculateNetSalary();
    }
    
    public Double getDeductions() {
        return deductions;
    }
    
    public void setDeductions(Double deductions) {
        this.deductions = deductions;
        this.netSalary = calculateNetSalary();
    }
    
    public Double getBonus() {
        return bonus;
    }
    
    public void setBonus(Double bonus) {
        this.bonus = bonus;
        this.netSalary = calculateNetSalary();
    }
    
    public Double getNetSalary() {
        return netSalary;
    }
    
    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", employee=" + (employee != null ? employee.getFullName() : "N/A") +
                ", salaryMonth=" + salaryMonth +
                ", basicSalary=" + basicSalary +
                ", netSalary=" + netSalary +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}