import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String username = "admin"; // name of user.
        String password = "password"; // password of user.

        System.out.println("Employee Management System");

        System.out.println("Username: ");
        String inputUsername = input.nextLine();

        System.out.println("Password: ");
        String inputPassword = input.nextLine();

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("Login Successful. " + username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again dear!");
        }
        input.close();
        
    }
}