import java.time.*;

class Attendance {
    int empId;
    LocalTime loginTime;
    LocalTime logoutTime;

    Attendance() {}

    void login() {
        loginTime = LocalTime.now();
        System.out.println("Login Time: " + loginTime);

        if (loginTime.isAfter(LocalTime.of(9, 0))) {
            System.out.println("Late Entry");
        }
    }

    void logout() {
        logoutTime = LocalTime.now();
        System.out.println("Logout Time: " + logoutTime);
    }

    void calculateHours() {
        if (loginTime != null && logoutTime != null) {
            long hours = Duration.between(loginTime, logoutTime).toHours();
            System.out.println("Working Hours: " + hours);

            if (hours >= 8) {
                System.out.println("Status: Present");
            } else if (hours >= 4) {
                System.out.println("Status: Half Day");
            } else {
                System.out.println("Status: Absent");
            }
        }
    }
}