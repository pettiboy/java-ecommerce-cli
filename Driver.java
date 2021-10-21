package UML;

import java.util.Scanner;
import java.util.Vector;

public class Driver {
    static boolean isAuthenticated = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vector<String> options = new Vector<>(2);
        options.add("Authenticate User");
        options.add("Add Product");
        options.add("Show Products");
        options.add("Exit");

        Products products = new Products();

        boolean flag = true;
        while (flag) {
            switch (Utils.showOptions(options, scanner)) {
            case 0:
                authenticateUser(scanner);
                break;
    
            case 1:
                products.addProduct(scanner);
                break;
    
            case 2:
                Utils.print(products.getProducts());
                break;
    
            default:
                flag = false;
                break;
            }
        }
        scanner.close();
    }    

    public static void authenticateUser(Scanner scanner) {
        // get phone number from user
        String userPhone = Integer.toString(Utils.getIntInRange("Phone: ", 10, 10, scanner));

        // initiate the PhoneOtp class
        PhoneOtp phoneOtp = new PhoneOtp(userPhone);

        // generate otp
        phoneOtp.generateOtp();

        while (true) {
            // get otp from user
            String userOtp = Integer.toString(Utils.getIntInRange("OTP: ", 6, 6, scanner));

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
