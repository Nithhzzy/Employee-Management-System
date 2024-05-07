import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private String name;
    private int id;
    private String position;

    public Employee(String name, int id, String position) {
        this.name = name; // user
        this.id = id; // staff 
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", position='" + position + '\'' +
                '}';
    }
}

class Position implements Serializable {
    private String title;
    private String responsibilities;

    public Position(String title, String responsibilities) {
        this.title = title;
        this.responsibilities = responsibilities;
    }

    public String getTitle() {
        return title;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                '}';
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_NAME = "data.ser";
    private static List<Employee> employees = new ArrayList<>();
    private static List<Position> positions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadDataFromFile();
        login();
    }

    private static void loadDataFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) in.readObject();
            positions = (List<Position>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }

    private static void saveDataToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employees);
            out.writeObject(positions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void login() {
        System.out.println("Welcome to Employee Management System");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // You can implement authentication logic here

        // For simplicity, let's assume any input is valid for now
        displayMainMenu();
    }

    private static void displayMainMenu() {
        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage Employees");
            System.out.println("2. Manage Positions");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageEmployees();
                    break;
                case 2:
                    managePositions();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    saveDataToFile();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } while (choice != 3);
    }

    private static void manageEmployees() {
        int choice;
        do {
            System.out.println("\nEmployee Management Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private static void managePositions() {
        int choice;
        do {
            System.out.println("\nPosition Management Menu:");
            System.out.println("1. Add Position");
            System.out.println("2. View Positions");
            System.out.println("3. Update Position");
            System.out.println("4. Delete Position");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPosition();
                    break;
                case 2:
                    viewPositions();
                    break;
                case 3:
                    updatePosition();
                    break;
                case 4:
                    deletePosition();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter employee position: ");
        String position = scanner.nextLine();

        Employee employee = new Employee(name, id, position);
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("\nList of Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new position: ");
                String position = scanner.nextLine();

                employee.name = name;
                employee.position = position;
                System.out.println("Employee updated successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Iterator<Employee> iterator = employees.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                System.out.println("Employee deleted successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    private static void addPosition() {
        System.out.print("Enter position title: ");
        String title = scanner.nextLine();
        System.out.print("Enter position responsibilities: ");
        String responsibilities = scanner.nextLine();

        Position position = new Position(title, responsibilities);
        positions.add(position);
        System.out.println("Position added successfully.");
    }

    private static void viewPositions() {
        if (positions.isEmpty()) {
            System.out.println("No positions found.");
        } else {
            System.out.println("\nList of Positions:");
            for (Position position : positions) {
                System.out.println(position);
            }
        }
    }

    private static void updatePosition() {
        System.out.print("Enter position title to update: ");
        String title = scanner.nextLine();

        boolean found = false;
        for (Position position : positions) {
            if (position.getTitle().equalsIgnoreCase(title)) {
                System.out.print("Enter new responsibilities: ");
                String responsibilities = scanner.nextLine();

                position.responsibilities = responsibilities;
                System.out.println("Position updated successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Position with title " + title + " not found.");
        }
    }

    private static void deletePosition() {
        System.out.print("Enter position title to delete: ");
        String title = scanner.nextLine();

        Iterator<Position> iterator = positions.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (position.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
                System.out.println("Position deleted successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Position with title " + title + " not found.");
        }
    }
}
