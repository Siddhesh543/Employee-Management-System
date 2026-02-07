package com.employeesystem;

import com.employeesystem.config.HibernateConfig;
import com.employeesystem.model.*;
import com.employeesystem.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Main Application Class - Employee Management System
 */
public class App {

    private static final EmployeeService employeeService = new EmployeeService();
    private static final DepartmentService departmentService = new DepartmentService();
    private static final DesignationService designationService = new DesignationService();
    private static final AttendanceService attendanceService = new AttendanceService();
    private static final SalaryService salaryService = new SalaryService();

    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        printWelcomeBanner();

        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            scanner.nextLine(); // buffer clear

            switch (choice) {
                case 1 -> employeeManagement();
                case 2 -> departmentManagement();
                case 3 -> designationManagement();
                case 4 -> attendanceManagement();
                case 5 -> salaryManagement();
                case 6 -> viewReports();
                case 0 -> {
                    running = false;
                    exitApplication();
                }
                default -> System.out.println("❌ Invalid choice!");
            }

            if (running) pressEnterToContinue();
        }
    }

    // ==================== UI ====================
    private static void printWelcomeBanner() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("        EMPLOYEE MANAGEMENT SYSTEM (Hibernate)");
        System.out.println("=".repeat(70));
    }

    private static void displayMainMenu() {
        System.out.println("""
                \n1. Employee Management
                2. Department Management
                3. Designation Management
                4. Attendance Management
                5. Salary Management
                6. Reports
                0. Exit
                """);
    }

    // ==================== EMPLOYEE ====================
    private static void employeeManagement() {
        System.out.println("""
                \n1. Add Employee
                2. View Employee by ID
                3. View All Employees
                4. Update Employee
                5. Delete Employee
                6. Search Employee
                0. Back
                """);

        System.out.print("Choice: ");
        int choice = getIntInput();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addEmployee();
            case 2 -> viewEmployeeById();
            case 3 -> employeeService.displayEmployeeList(employeeService.getAllEmployees());
            case 4 -> updateEmployee();
            case 5 -> deleteEmployee();
            case 6 -> searchEmployee();
        }
    }

    private static void addEmployee() {
        System.out.println("\n--- Add Employee ---");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        List<Department> departments = departmentService.getActiveDepartments();
        if (departments.isEmpty()) {
            System.out.println("❌ No Department Found");
            return;
        }

        departments.forEach(d ->
                System.out.println(d.getDepartmentId() + " - " + d.getDepartmentName()));

        System.out.print("Department ID: ");
        Long deptId = getLongInput();
        scanner.nextLine();
        Department department = departmentService.getDepartmentById(deptId);

        List<Designation> designations = designationService.getActiveDesignations();
        designations.forEach(d ->
                System.out.println(d.getDesignationId() + " - " + d.getDesignationName()));

        System.out.print("Designation ID: ");
        Long desigId = getLongInput();
        scanner.nextLine();
        Designation designation = designationService.getDesignationById(desigId);

        System.out.print("Salary: ");
        Double salary = getDoubleInput();
        scanner.nextLine();

        System.out.print("Hire Date (yyyy-MM-dd): ");
        String hireDateStr = scanner.nextLine();

        try {
            Date hireDate = dateFormat.parse(hireDateStr);
            Employee employee = new Employee(
                    firstName, lastName, email, phone, password,
                    department, designation, salary, hireDate
            );
            employeeService.addEmployee(employee);
            System.out.println("✅ Employee Added Successfully");
        } catch (ParseException e) {
            System.out.println("❌ Invalid Date Format");
        }
    }

    private static void viewEmployeeById() {
        System.out.print("Employee ID: ");
        Long id = getLongInput();
        scanner.nextLine();
        employeeService.displayEmployee(employeeService.getEmployeeById(id));
    }

    private static void updateEmployee() {
        System.out.print("Employee ID: ");
        Long id = getLongInput();
        scanner.nextLine();

        Employee e = employeeService.getEmployeeById(id);
        if (e == null) {
            System.out.println("❌ Employee Not Found");
            return;
        }

        System.out.print("Phone (" + e.getPhone() + "): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) e.setPhone(phone);

        System.out.print("Salary (" + e.getSalary() + "): ");
        String salary = scanner.nextLine();
        if (!salary.isEmpty()) e.setSalary(Double.parseDouble(salary));

        employeeService.updateEmployee(e);
        System.out.println("✅ Employee Updated");
    }

    private static void deleteEmployee() {
        System.out.print("Employee ID: ");
        Long id = getLongInput();
        scanner.nextLine();
        employeeService.deleteEmployee(id);
        System.out.println("✅ Employee Deleted");
    }

    private static void searchEmployee() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        employeeService.displayEmployeeList(
                employeeService.searchEmployeesByName(name)
        );
    }

    // ==================== DEPARTMENT ====================
    private static void departmentManagement() {
        System.out.println("""
                \n1. Add Department
                2. View Departments
                3. Delete Department
                """);

        System.out.print("Choice: ");
        int c = getIntInput();
        scanner.nextLine();

        if (c == 1) {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Description: ");
            String desc = scanner.nextLine();
            System.out.print("Location: ");
            String loc = scanner.nextLine();
            departmentService.addDepartment(new Department(name, desc, loc));
        } else if (c == 2) {
            departmentService.displayDepartmentList(departmentService.getAllDepartments());
        } else if (c == 3) {
            System.out.print("Department ID: ");
            departmentService.deleteDepartment(getLongInput());
            scanner.nextLine();
        }
    }

    // ==================== DESIGNATION ====================
    private static void designationManagement() {
        System.out.println("""
                \n1. Add Designation
                2. View Designations
                """);

     
        System.out.print("Choice: ");
        int c = getIntInput();
        scanner.nextLine();

        if (c == 1) {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Description: ");
            String desc = scanner.nextLine();
            System.out.print("Level: ");
            String level = scanner.nextLine();
            designationService.addDesignation(new Designation(name, desc, level));
        } else if (c == 2) {
            designationService.displayDesignationList(designationService.getAllDesignations());
        }
    }

    // ==================== ATTENDANCE ====================
    private static void attendanceManagement() {
        System.out.print("Employee ID: ");
        Long empId = getLongInput();
        scanner.nextLine();

        System.out.print("Date (yyyy-MM-dd): ");
        String d = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        try {
            attendanceService.markAttendance(
                    new Attendance(
                            employeeService.getEmployeeById(empId),
                            dateFormat.parse(d),
                            new Date(), null, status
                    )
            );
            System.out.println("✅ Attendance Marked");
        } catch (ParseException e) {
            System.out.println("❌ Invalid Date");
        }
    }

    // ==================== SALARY ====================
    private static void salaryManagement() {
        System.out.print("Employee ID: ");
        Long empId = getLongInput();
        scanner.nextLine();

        System.out.print("Month (yyyy-MM-dd): ");
        String m = scanner.nextLine();

        System.out.print("Basic Salary: ");
        Double basic = getDoubleInput();
        scanner.nextLine();

        Salary salary = new Salary(
                employeeService.getEmployeeById(empId),
                parseDate(m), basic, 0.0, 0.0, 0.0
        );
        salaryService.processSalary(salary);
        System.out.println("✅ Salary Processed");
    }

    private static Date parseDate(String d) {
        try {
            return dateFormat.parse(d);
        } catch (Exception e) {
            return new Date();
        }
    }

    // ==================== REPORT ====================
    private static void viewReports() {
        System.out.println("Employees: " + employeeService.getTotalEmployeeCount());
        System.out.println("Departments: " + departmentService.getTotalDepartmentCount());
        System.out.println("Designations: " + designationService.getTotalDesignationCount());
    }

    // ==================== UTIL ====================
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Enter number: ");
        }
        return scanner.nextInt();
    }

    private static Long getLongInput() {
        while (!scanner.hasNextLong()) {
            scanner.next();
            System.out.print("Enter valid ID: ");
        }
        return scanner.nextLong();
    }

    private static Double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Enter number: ");
        }
        return scanner.nextDouble();
    }

    private static void pressEnterToContinue() {
        System.out.print("\nPress Enter...");
        scanner.nextLine();
    }

    private static void exitApplication() {
        HibernateConfig.shutdown();
        scanner.close();
        System.out.println("👋 Application Closed");
    }
}
