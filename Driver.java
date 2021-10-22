package UML;

import java.util.Scanner;
import java.util.Vector;

public class Driver {
    static User user;
    static Order order;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        Vector<String> options = new Vector<>();
        options.add("Authenticate User");
        options.add("Add Product");
        options.add("Show Products");
        options.add("Add To Cart");
        options.add("Exit");

        Products products = new Products();

        boolean flag = true;
        while (flag) {
            switch (Utils.showOptions(options, scanner)) {
            case 0:
                authenticateUser(scanner);
                break;
    
            case 1:
                if (user.isStaff) {
                    products.addProduct(scanner);
                } else {
                    Utils.print("You do not have permission to add products.");
                }
                break;
    
            case 2:
                Utils.print(products.getProducts());
                break;
            
            case 3:
                if (order != null) {
                    boolean validProductId = false;
                    int productId = 1; 
                    while (!validProductId) {
                        productId = Utils.getIntInRange("Add to cart, Product ID: ", 1, (int) Utils.numOfLinesIn("products.csv"), scanner);
                        Product product = products.productIdToProduct(productId);
                        if (product != null) {
                            Utils.print(product.name + " Added to cart!");
                            validProductId = true;
                        } else {
                            Utils.print("Invalid Product ID, try again...");
                        }
                    }
                    order.addToCart(productId);
                } else {
                    Utils.print("Login to explore this feature...");
                }
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
        String userPhone = "" + Utils.getBigIntInRange("Phone: ", 10, 10, scanner);

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
                user = new User(userPhone, scanner);
                order = new Order(user);
                return;
            }
            Utils.print("Incorrect OTP. Try again...");
        }
    }

}
