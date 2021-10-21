package UML;

import java.util.Scanner;
import java.util.Vector;

public class Driver {
    static boolean isAuthenticated = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        print(getIntInRange(10, 10, scanner));

        Vector<String> options = new Vector<>(2);
        options.add("Authenticate User");
        options.add("Exit");

        switch (showOptions(options, scanner)) {
        case 0:
            authenticateUser(scanner);
            break;

        case 1:
            break;

        default:
            break;
        }

        scanner.close();
    }

    private static int showOptions(Vector<String> options, Scanner scanner) {
        // create a vector to store valid options ids
        Vector<Integer> validOptions = new Vector<>(options.size());

        // add option ids to the vector and print what that option does
        for (int i = 0; i < options.size(); i++) {
            validOptions.add(i + 1);
            print(i + 1 + ") " + options.get(i));
        }

        // keep asking user for input till correct choice is chosen
        while (true) {
            Scanner input = scanner;
            int choice = Integer.parseInt(input.next());
            if (validOptions.contains(choice)) {
                return choice - 1;
            }
            print("Invalid choice. Try again.");
        }
    }

    public static void authenticateUser(Scanner scanner) {
        // get phone number from user
        print("Phone: ", true);
        String userPhone = getIntInRange(10, 10, scanner);

        // initiate the PhoneOtp class
        PhoneOtp phoneOtp = new PhoneOtp(userPhone);

        // generate otp
        phoneOtp.generateOtp();

        while (true) {
            // get otp from user
            print("OTP: ", true);
            String userOtp = getIntInRange(6, 6, scanner);

            // validate otp
            if (userOtp.equals("exit")) {
                return;
            } else if (phoneOtp.validateOtp(userPhone, userOtp)) {
                isAuthenticated = true;
                return;
            }
            print("Incorrect OTP. Try again or type 'exit' to quit.");
        }
    }

    private static String getIntInRange(int minLength, int maxLength, Scanner scanner) {
        int validInteger = -1;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                validInteger = scanner.nextInt();
                if (String.valueOf(validInteger).length() <= maxLength
                        && String.valueOf(validInteger).length() >= minLength) {
                    break;
                } else {
                    print("Invalid input integer. Try again...");
                }
            } else {
                print("Invalid input. Must be an integer, try again...");
                scanner.next();
            }
        }
        return Integer.toString(validInteger);
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

    // private static void print(Integer printThis) {
    //     System.out.println(printThis);
    // }

}
