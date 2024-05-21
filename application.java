import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Application {
    private final String name;
    private final String username;
    private final String password;
    private final String position;
    private final double salary;

    public Application(String name, String username, String password, String position, double salary) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Username: " + username + ", Position: " + position + ", Salary: " + salary;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

class EmployeeManagementSystem {
    private final Map<String, Application> employees;
    private final Scanner scanner;

    public EmployeeManagementSystem() {
        employees = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addEmployee(String name, String username, String password, String position, double salary) {
        Application employee = new Application(name, username, password, position, salary);
        employees.put(username, employee);
        System.out.println("Employee added successfully.");
    }

    public void displayEmployees() {
        System.out.println("Employee List:");
        for (Application employee : employees.values()) {
            System.out.println(employee);
        }
    }

    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Application loggedInUser = null;
        for (Application employee : employees.values()) {
            if (employee.authenticate(username, password)) {
                loggedInUser = employee;
                break;
            }
        }

        if (loggedInUser != null) {
            System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
            System.out.println("Your position is: " + loggedInUser.getPosition());
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}

public class ApplicationMain {
    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem();
        system.addEmployee("Som Nang", "nang", "123", "Tester", 30000);
        system.addEmployee("Ro Ma", "roma", "123", "Developer", 60000);
        system.addEmployee("Nithh", "nith", "123", "Coder", 80000);

        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Login");
            System.out.println("2. Add Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.login();
                    break;
                case 2:
                    System.out.println("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.println("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.println("Enter salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    system.addEmployee(name, username, password, position, salary);
                    break;
                case 3:
                    system.displayEmployees();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
