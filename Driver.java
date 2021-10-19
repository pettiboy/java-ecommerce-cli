package UML;

import java.util.Scanner;
import java.util.Vector;

public class Driver {
    public static void main(String[] args) {
        // authenticateUser();
        int choice = initialOption();
        System.out.println("choice selected " + choice);

    }

    private static int initialOption() {
        Vector<Integer> validOptions = new Vector<>(3);
        validOptions.add(1);
        validOptions.add(2);
        validOptions.add(3);

        System.out.println("1. Login \n2. View Catalouge");
        boolean validOption = false;
        while (!validOption) {
            Scanner input = new Scanner(System.in);
            int choice = Integer.parseInt(input.next());

            if (validOptions.contains(choice)) {
                input.close();
                return choice;
            }

            System.out.println("Invalid choice. Try again.");
        }
        return 0;
    }

    public static boolean authenticateUser() {
        // get phone number from user
        System.out.print("Phone: ");
        String userPhone = getString();

        // initiate the PhoneOtp class
        PhoneOtp phoneOtp = new PhoneOtp(userPhone);

        // generate otp
        phoneOtp.generateOtp();

        while (true) {
            // get otp from user
            System.out.print("OTP: ");
            String userOtp = getString();

            // validate otp
            if (userOtp.equals("exit")) {
                return false;
            } else if (phoneOtp.validateOtp(userPhone, userOtp)) {
                return true;
            }
            System.out.println("Incorrect OTP. Try again or type 'exit' to quit.");
        }
    }

    public static String getString() {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        return name;
    }
}
