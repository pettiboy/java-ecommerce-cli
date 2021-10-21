package UML;

import java.util.Scanner;
import java.util.Vector;

public class Driver {
    boolean isAuthenticated = false;

    public static void main(String[] args) {
        Vector<String> options = new Vector<>(2);
        options.add("try this");
        options.add("try also");
        print(showOptions(options));
    }

    private static int showOptions(Vector<String> options) {
        // create a vector to store valid options ids
        Vector<Integer> validOptions = new Vector<>(options.size());

        // add option ids to the vector and print what that option does
        for (int i = 0; i < options.size(); i++) {
            validOptions.add(i + 1);
            print(i + 1 + ") " + options.get(i));
        }

        // keep asking user for input till correct choice is chosen
        while (true) {
            Scanner input = new Scanner(System.in);
            int choice = Integer.parseInt(input.next());
            if (validOptions.contains(choice)) {
                input.close();
                return choice - 1;
            }
            print("Invalid choice. Try again.");
        }
    }

    public void authenticateUser() {
        // get phone number from user
        print("Phone: ", true);
        String userPhone = getString();

        // initiate the PhoneOtp class
        PhoneOtp phoneOtp = new PhoneOtp(userPhone);

        // generate otp
        phoneOtp.generateOtp();

        while (true) {
            // get otp from user
            print("OTP: ", true);
            String userOtp = getString();

            // validate otp
            if (userOtp.equals("exit")) {
                return;
            } else if (phoneOtp.validateOtp(userPhone, userOtp)) {
                this.isAuthenticated = true;
                return;
            }
            print("Incorrect OTP. Try again or type 'exit' to quit.");
        }
    }

    public static String getString() {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        return name;
    }

    // handle printing to console using method overloading
    private static void print(String printThis, boolean noNewLine) {
        if (noNewLine) {
            System.out.print(printThis);
        }
    }
    private static void print(String printThis) {
        System.out.println(printThis);
    }
    private static void print(Integer printThis) {
        System.out.println(printThis);
    }
    
}
