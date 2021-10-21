package UML;

import java.util.Scanner;
import java.util.Vector;

public class Utils {

    public static int showOptions(Vector<String> options, Scanner scanner) {
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

    // getting values
    public static String getStringInRange(String prompt, int minLength, int maxLength, Scanner scanner) {
        String validString = "";
        print(prompt, true);
        while (scanner.hasNext()) {
            validString = scanner.next().toString();
            if (validString.length() <= maxLength && validString.length() >= minLength) {
                break;
            } else {
                print("Invalid input String. Try again...");
                print(prompt, true);
            }
        }
        return validString;
    }
    public static int getIntInRange(String prompt, int minLength, int maxLength, Scanner scanner) {
        int validInteger = -1;
        print(prompt, true);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                validInteger = scanner.nextInt();
                if (String.valueOf(validInteger).length() <= maxLength
                        && String.valueOf(validInteger).length() >= minLength) {
                    break;
                } else {
                    print("Invalid input integer. Try again...");
                    print(prompt, true);
                }
            } else {
                print("Invalid input. Must be an integer, try again...");
                print(prompt, true);
                scanner.next();
            }
        }
        return validInteger;
    }
    public static Double getDoubleInRange(String prompt, Double minLength, Double maxLength, Scanner scanner) {
        Double validDouble = -1.00;
        print(prompt, true);
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                validDouble = scanner.nextDouble();
                if (String.valueOf(validDouble).length() <= maxLength
                        && String.valueOf(validDouble).length() >= minLength) {
                    break;
                } else {
                    print("Invalid input double. Try again...");
                    print(prompt, true);
                }
            } else {
                print("Invalid input. Must be an double, try again...");
                print(prompt, true);
                scanner.next();
            }
        }
        print("", true);
        return validDouble;
    }

    // handle printing to console using method overloading
    public static void print(String printThis, boolean noNewLine) {
        if (noNewLine) {
            System.out.print(printThis);
        }
    }

    public static void print(String printThis) {
        System.out.println(printThis);
    }

    public static void print(Integer printThis) {
        System.out.println(printThis);
    }
    
    public static void print(Vector<Product> printThis) {
        for (Product element : printThis) {
            System.out.println(element);
            System.out.println();
        }
    }
}
