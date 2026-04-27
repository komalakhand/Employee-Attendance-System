import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Attendance> records = new HashMap<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n1. Login");
            System.out.println("2. Logout");
            System.out.println("3. View Records");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            if (choice == 4) {
                System.exit(0);
            }

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            if (id <= 0) {
                System.out.println("Invalid ID!");
                continue;
            }

            Attendance att = records.getOrDefault(id, new Attendance());
            att.empId = id;

            switch (choice) {

                case 1:
                    att.login();
                    break;

                case 2:
                    att.logout();
                    att.calculateHours();
                    break;

                case 3:
                    for (Integer key : records.keySet()) {
                        System.out.println("Employee ID: " + key);
                    }
                    break;
            }

            records.put(id, att);
        }
    }
}