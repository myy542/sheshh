package mylene;
import java.util.Scanner;

public class Mylene {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
boolean exit=true;

do {
 System.out.println("\n---------------PAYSLIP MANAGEMENT SYSTEM---------------");
            System.out.println("1. EMPLOYEE");
            System.out.println("2. GENERATE PAYSLIP");
            System.out.println("3. INDIVIDUAL REPORT");
            System.out.println("4. GENERAL REPORT");
            System.out.println("5. EXIT");
            System.out.println("\n-------------------------------------------------------");

            System.out.print("Enter Action: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Employee em = new Employee();
                    em.employeeInfo();
                    break;
                case 2:
                    Payslip ps = new Payslip();
                    ps.Payslip();
                    break;
                case 3:
                    Report indReport = new Report();
                    indReport.IndividualReport();
                    break;
                case 4:
                    Report genReport = new Report();
                    genReport.GeneralReport();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (exit);
    }
}