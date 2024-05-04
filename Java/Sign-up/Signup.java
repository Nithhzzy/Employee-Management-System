import java.util.Scanner;

public class Signup {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Employee Management System - Sign Up Form\n");

        System.out.print("Enter your username: ");
        String username = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Confirm your password: ");
        String confirmPassword = input.nextLine();

        // Validate if the passwords match
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Please try again.");
        } else {
            // Save the user credentials to a database or file
            System.out.println("Sign up successful. " + username + "!");
        }

        input.close();
    }
}