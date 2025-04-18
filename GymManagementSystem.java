import java.util.*;

class User {
    String username;
    String password;
    String role;
    String membership;
    List<String> activities;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.membership = "Basic";
        this.activities = new ArrayList<>();
    }
}

public class GymManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<String> availableActivities = new ArrayList<>(Arrays.asList("Yoga", "Cardio", "Weight Training", "Swimming"));

    static {
        users.add(new User("admin", "admin123", "admin"));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========= GYM MANAGEMENT SYSTEM =========");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: login(); break;
                case 2: register(); break;
                case 3: System.out.println("Thank you!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    static void login() {
        System.out.print("\nUsername: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        for (User u : users) {
            if (u.username.equals(username) && u.password.equals(password)) {
                System.out.println("\nLogin successful as " + u.role);
                if (u.role.equals("admin")) adminMenu();
                else userMenu(u);
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    static void register() {
        System.out.print("\nChoose username: ");
        String username = sc.nextLine();
        for (User u : users) {
            if (u.username.equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }
        System.out.print("Choose password: ");
        String password = sc.nextLine();
        users.add(new User(username, password, "user"));
        System.out.println("Registration successful.");
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n========= ADMIN MENU =========");
            System.out.println("1. View All Users");
            System.out.println("2. Update User Membership");
            System.out.println("3. Delete User");
            System.out.println("4. View Activities List");
            System.out.println("5. Add New Activity");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                System.out.println("\n-- Registered Users --");
                for (User u : users) {
                    System.out.println(u.username + " (" + u.role + ") - Membership: " + u.membership + ", Activities: " + u.activities);
                }
            } else if (ch == 2) {
                System.out.print("Enter username to update membership: ");
                String uname = sc.nextLine();
                for (User u : users) {
                    if (u.username.equals(uname) && u.role.equals("user")) {
                        System.out.print("Enter new membership (Basic/Premium/VIP): ");
                        u.membership = sc.nextLine();
                        System.out.println("Membership updated.");
                        break;
                    }
                }
            } else if (ch == 3) {
                System.out.print("Enter username to delete: ");
                String uname = sc.nextLine();
                users.removeIf(u -> u.username.equals(uname) && u.role.equals("user"));
                System.out.println("User deleted if existed.");
            } else if (ch == 4) {
                System.out.println("Available Activities: " + availableActivities);
            } else if (ch == 5) {
                System.out.print("Enter new activity name: ");
                String newActivity = sc.nextLine();
                if (!availableActivities.contains(newActivity)) {
                    availableActivities.add(newActivity);
                    System.out.println("Activity added.");
                } else {
                    System.out.println("Activity already exists.");
                }
            } else if (ch == 6) return;
            else System.out.println("Invalid option.");
        }
    }

    static void userMenu(User user) {
        while (true) {
            System.out.println("\n========= USER MENU =========");
            System.out.println("Welcome, " + user.username);
            System.out.println("1. View Profile");
            System.out.println("2. Update Password");
            System.out.println("3. View Membership");
            System.out.println("4. Join Activity");
            System.out.println("5. My Activities");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                System.out.println("\nUsername: " + user.username);
                System.out.println("Membership: " + user.membership);
                System.out.println("Activities: " + user.activities);
            } else if (ch == 2) {
                System.out.print("Enter new password: ");
                user.password = sc.nextLine();
                System.out.println("Password updated.");
            } else if (ch == 3) {
                System.out.println("Your Membership Plan: " + user.membership);
            } else if (ch == 4) {
                System.out.println("Available Activities: " + availableActivities);
                System.out.print("Enter activity to join: ");
                String activity = sc.nextLine();
                if (availableActivities.contains(activity)) {
                    if (!user.activities.contains(activity)) {
                        user.activities.add(activity);
                        System.out.println("You have joined " + activity);
                    } else {
                        System.out.println("You already joined this activity.");
                    }
                } else {
                    System.out.println("Activity not found.");
                }
            } else if (ch == 5) {
                System.out.println("Your Activities: " + user.activities);
            } else if (ch == 6) return;
            else System.out.println("Invalid option.");
        }
    }
}
