package UML;

import java.util.Scanner;
import java.util.Vector;

public class Driver {
    static boolean isAuthenticated = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vector<String> options = new Vector<>(2);
        options.add("Authenticate User");
        options.add("Show Products");
        options.add("Exit");

        switch (Utils.showOptions(options, scanner)) {
        case 0:
            authenticateUser(scanner);
            break;

        case 1:
            Products products = new Products();
            products.addProduct();
            Utils.print(products.getProducts());
            break;

        default:
            break;
        }

        scanner.close();
    }    

    public static void authenticateUser(Scanner scanner) {
        // get phone number from user
        Utils.print("Phone: ", true);
        String userPhone = Utils.getIntInRange(10, 10, scanner);

        // initiate the PhoneOtp class
        PhoneOtp phoneOtp = new PhoneOtp(userPhone);

        // generate otp
        phoneOtp.generateOtp();

        while (true) {
            // get otp from user
            Utils.print("OTP: ", true);
            String userOtp = Utils.getIntInRange(6, 6, scanner);

            // validate otp
            if (userOtp.equals("exit")) {
                return;
            } else if (phoneOtp.validateOtp(userPhone, userOtp)) {
                isAuthenticated = true;
                return;
            }
            Utils.print("Incorrect OTP. Try again or type 'exit' to quit.");
        }
    }

}
