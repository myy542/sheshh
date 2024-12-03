package mylene;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Report {
   
    public void IndividualReport() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Employee ID to generate report: ");
        int employeeId = getValidPositiveIntInput(sc);

        String query = "SELECT e.e_id, e.fname, e.lname, e.position, e.department, " +
                       "p.basic_salary, p.allowances, p.deductions, p.overtime_pay, p.bonuses, p.gross_salary, p.net_salary " +
                       "FROM tbl_employee e " +
                       "INNER JOIN tbl_gpayslip p ON e.e_id = p.e_id " +
                       "WHERE e.e_id = ?";

        String[] headers = {"ID", "First Name", "Last Name", "Position", "Department", "Basic Salary", "Allowances", "Deductions", "Overtime", "Bonuses", "Gross Salary", "Net Salary"};
        String[] columns = {"e_id", "fname", "lname", "position", "department", "basic_salary", "allowances", "deductions", "overtime_pay", "bonuses", "gross_salary", "net_salary"};

        conf.viewRecordsWithParameter(query, headers, columns, employeeId);
    }

    public void GeneralReport() {
        config conf = new config();

        String query = "SELECT e.e_id, e.fname, e.lname, e.position, e.department, " +
                       "p.basic_salary, p.allowances, p.deductions, p.overtime_pay, p.bonuses, p.gross_salary, p.net_salary " +
                       "FROM tbl_employee e " +
                       "INNER JOIN tbl_gpayslip p ON e.e_id = p.e_id";

        String[] headers = {"ID", "First Name", "Last Name", "Position", "Department", "Basic Salary", "Allowances", "Deductions", "Overtime", "Bonuses", "Gross Salary", "Net Salary"};
        String[] columns = {"e_id", "fname", "lname", "position", "department", "basic_salary", "allowances", "deductions", "overtime_pay", "bonuses", "gross_salary", "net_salary"};

        // Fetch and view the records
        conf.viewRecords(query, headers, columns);

        // Now calculate total net salary
        double totalNetSalary = calculateTotalNetSalary(query);
        System.out.println("Total Net Salary of all employees: " + totalNetSalary);
    }

    private double calculateTotalNetSalary(String query) {
        config conf = new config();
        double totalNetSalary = 0;

        // Fetch the result set for the query
        ResultSet rs = conf.getResultSet(query);  // Assuming getResultSet method exists in config class.

        try {
            while (rs.next()) {
                totalNetSalary += rs.getDouble("net_salary"); // Add net salary to total
            }
        } catch (SQLException e) {
            System.out.println("Error calculating total net salary: " + e.getMessage());
        }

        return totalNetSalary;
    }

    private int getValidPositiveIntInput(Scanner sc) {
        int value = -1;
        while (true) {
            try {
                value = sc.nextInt();
                if (value > 0) break;
                System.out.print("Invalid input. Please enter a positive number: ");
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
                sc.next();
            }
        }
        return value;
    }
}
